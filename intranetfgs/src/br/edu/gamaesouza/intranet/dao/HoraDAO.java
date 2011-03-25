package br.edu.gamaesouza.intranet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.edu.gamaesouza.intranet.bean.Aluno;
import br.edu.gamaesouza.intranet.bean.Atividade;
import br.edu.gamaesouza.intranet.bean.Hora;
import br.edu.gamaesouza.intranet.bean.HoraAEP;
import br.edu.gamaesouza.intranet.bean.HoraComplementar;
import br.edu.gamaesouza.intranet.bean.result.HorasAtividadeResultBean;
import br.edu.gamaesouza.intranet.bean.result.HorasCursoResultBean;

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
	
	
}
