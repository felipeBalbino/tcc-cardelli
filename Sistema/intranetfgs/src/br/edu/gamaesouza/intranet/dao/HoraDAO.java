package br.edu.gamaesouza.intranet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Atividade;
import br.edu.gamaesouza.intranet.bean.Hora;
import br.edu.gamaesouza.intranet.bean.HoraAEP;
import br.edu.gamaesouza.intranet.bean.HoraComplementar;
import br.edu.gamaesouza.intranet.bean.result.HorasAtividadeResultBean;
import br.edu.gamaesouza.intranet.bean.result.HorasCursoResultBean;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class HoraDAO extends HibernateDaoSupport {

	public List<HoraAEP> getHorasAEP(Aluno aluno){
		Query horasAEPQuery = getSession().getNamedQuery("horasAEPByAluno");
		horasAEPQuery.setInteger("aluno", aluno.getId());
		return horasAEPQuery.list();
	}
	
	public List<HoraComplementar> getHorasComplementares(Aluno aluno){
		Query horasAEPQuery = getSession().getNamedQuery("horasComplementaresByAluno");
		horasAEPQuery.setInteger("aluno", aluno.getId());
		return horasAEPQuery.list();
	}
	
	public List<HoraComplementar> getHorasComplementaresByAtividade(Aluno aluno, Atividade atividade){
		Query horasAEPQuery = getSession().getNamedQuery("horasComplementaresByAlunoAtividade");
		horasAEPQuery.setInteger("aluno", aluno.getId());
		horasAEPQuery.setInteger("atividade", atividade.getId());
		return horasAEPQuery.list();
	}
	
	public List<HoraAEP> getHorasAEPByAnoSemestre(Aluno aluno,Integer ano,Integer semestre){
		Query horasAEPQuery = getSession().getNamedQuery("horasAEPByAlunoAnoSemestre");
		horasAEPQuery.setInteger("aluno", aluno.getId());
		horasAEPQuery.setInteger("ano", ano);
		horasAEPQuery.setInteger("semestre", semestre);
		return horasAEPQuery.list();
	}
	
	public void save(Hora hora){
		getHibernateTemplate().save(hora);
	}

	public List<HorasAtividadeResultBean> getHorasGroupByAtividade(Integer id) {
		Query horasGroupByAtividade = getSession().getNamedQuery("totalHorasPorAtividade");
		horasGroupByAtividade.setInteger("aluno",id);
		return horasGroupByAtividade.list();
	}
	
	public List<HorasCursoResultBean> getHorasCursoAndAluno(Integer id) {
		Query horasCursoAndAluno = getSession().getNamedQuery("totalHorasCursoEAluno");
		horasCursoAndAluno.setInteger("aluno",id);
		return horasCursoAndAluno.list();
	}

	public List<Atividade> getAtividades() {
		Criteria c = getSession().createCriteria(Atividade.class);
		return c.list();
	}

	public Atividade getAtividadeById(Integer id) {
		Criteria c = getSession().createCriteria(Atividade.class);
		c.add(Restrictions.eq("id", id));
		return (Atividade) c.uniqueResult();
	}

	public HoraComplementar getHorasComplementares(Hora hora) {
		HoraComplementar horaComplementar = null;
		if(hora instanceof HoraComplementar){
			horaComplementar = (HoraComplementar) hora;
		}else{
			return null;
		}
		
		
		
		Query horasCursoAndAluno = getSession().getNamedQuery("horaComplementarByHora");
		horasCursoAndAluno.setInteger("aluno",horaComplementar.getAluno().getId());
		horasCursoAndAluno.setString("titulo",horaComplementar.getTitulo());
		horasCursoAndAluno.setInteger("minutos", horaComplementar.getMinutos());
		return (HoraComplementar) horasCursoAndAluno.uniqueResult();

	}

	public HoraComplementar getHoraComplementarById(Integer id) {
		Criteria c = getSession().createCriteria(HoraComplementar.class);
		c.add(Restrictions.eq("id", id));
		return (HoraComplementar) c.uniqueResult();
	}

	public void deleteComplementar(String alunoId, String horaId) throws IntranetException {
		
		Query query = getSession().getNamedQuery("horasByAlunoAndIdHora");
		query.setInteger("aluno", Integer.parseInt(alunoId));
		query.setInteger("hora", Integer.parseInt(horaId));
		
		Hora hora = (Hora) query.uniqueResult();
		
		if(hora == null){
			throw new IntranetException("[deleteComplementar] Não foi possível buscar a hora com os parâmetros passados.");
		}else{
			getHibernateTemplate().delete(hora);
		}
		
	}

	public void deleteAEP(String alunoId, String horaId) throws IntranetException {
		Query query = getSession().getNamedQuery("horasByAlunoAndIdHora");
		query.setInteger("aluno", Integer.parseInt(alunoId));
		query.setInteger("hora", Integer.parseInt(horaId));
		
		Hora hora = (Hora) query.uniqueResult();
		
		if(hora == null){
			throw new IntranetException("[deleteAEP] Não foi possível buscar a hora com os parâmetros passados.");
		}else{
			getHibernateTemplate().delete(hora);
		}
		
	}

	public List<HoraComplementar> getHorasComplementares(Aluno aluno,
			Integer atividadeKey) {
		
		if (atividadeKey == null || atividadeKey < 1){
			Query horasAEPQuery = getSession().getNamedQuery("horasComplementaresByAluno");
			horasAEPQuery.setInteger("aluno", aluno.getId());
			return horasAEPQuery.list();
		}else{
			Query horasAEPQuery = getSession().getNamedQuery("horasComplementaresByAlunoAtividade");
			horasAEPQuery.setInteger("aluno", aluno.getId());
			horasAEPQuery.setInteger("atividade", atividadeKey);
			return horasAEPQuery.list();
		}
		
		
	}
	
	
}
