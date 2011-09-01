package br.edu.gamaesouza.intranet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.edu.gamaesouza.intranet.bean.Evento;
import br.edu.gamaesouza.intranet.bean.Vaga;
import br.edu.gamaesouza.intranet.utils.IntranetException;

/**
 * @author Felipe Balbino
 * @since 02/06/2011
 */
public class VagaDAO extends HibernateDaoSupport {
	
	public void save(Vaga vaga) throws IntranetException{
		getHibernateTemplate().save(vaga);
	}
	
	public void update(Vaga vaga) throws IntranetException{	
		getHibernateTemplate().update(vaga);
	}
	
	public Vaga merge(Vaga vaga) throws IntranetException{
		return (Vaga)getHibernateTemplate().merge(vaga);
	}
	
	public void delete(Vaga vaga) throws IntranetException{
		getHibernateTemplate().delete(vaga);	
	}
	
	public List<Vaga> getAll() throws IntranetException{
		Criteria allVagas = getSession().createCriteria(Vaga.class);
		List<Vaga> vagasList = (List<Vaga>)allVagas.list();
		return vagasList;
	}
	
	public List<Vaga> getAllValidos() throws IntranetException{
		Criteria allVagas = getSession().createCriteria(Vaga.class);
		allVagas.add(Restrictions.eq("confirmacao", true));
		allVagas.add(Restrictions.eq("seAtivo", true));
		List<Vaga> vagasList = (List<Vaga>)allVagas.list();
		return vagasList;
	}

	public List<Vaga> getAllForIndex() throws IntranetException{
		Criteria allVagasForIndex = getSession().createCriteria(Vaga.class);
		allVagasForIndex.setMaxResults(5);
		allVagasForIndex.addOrder(Order.desc("id"));	
		List<Vaga> vagasList = allVagasForIndex.list();
		return vagasList;
	}
	
	public Vaga getVagaById(Long idVaga) throws IntranetException{
		Criteria vagaById = getSession().createCriteria(Vaga.class);
		vagaById.add(Restrictions.eq("id", idVaga));
		Vaga vaga = (Vaga) vagaById.uniqueResult();
		return vaga;
	}
	
	public List<Vaga> getVagasByIdAluno(Integer idAluno) throws IntranetException{
		Criteria vagasByAluno = getSession().createCriteria(Vaga.class);
		vagasByAluno.add(Restrictions.eq("publicador.id", idAluno));
		List <Vaga> vagas = (List<Vaga>) vagasByAluno.list();
		return vagas;
	}
	

}
