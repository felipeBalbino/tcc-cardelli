package br.edu.gamaesouza.intranet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.edu.gamaesouza.intranet.bean.AreaProfissional;
import br.edu.gamaesouza.intranet.bean.DisciplinaLetiva;
import br.edu.gamaesouza.intranet.bean.Empresa;
import br.edu.gamaesouza.intranet.bean.Evento;
import br.edu.gamaesouza.intranet.bean.Vaga;
import br.edu.gamaesouza.intranet.utils.IntranetException;

/**
 * @author Felipe Balbino
 * @since 02/06/2011
 */
public class AreaProfissionalDAO extends HibernateDaoSupport {
	
	public void save(AreaProfissional areaProfissional) throws IntranetException{
		getHibernateTemplate().save(areaProfissional);
	}
	
	public void update(AreaProfissional areaProfissional) throws IntranetException{	
		getHibernateTemplate().update(areaProfissional);
	}
	
	public void merge(AreaProfissional areaProfissional) throws IntranetException{
		getHibernateTemplate().merge(areaProfissional);
	}
	
	
	public void delete(AreaProfissional areaProfissional) throws IntranetException{
		getHibernateTemplate().delete(areaProfissional);	
	}
	
	public List<AreaProfissional> getAll() throws IntranetException{
		Criteria allAreaProfissional = getSession().createCriteria(AreaProfissional.class);
		List<AreaProfissional> areaProfissionalList = (List<AreaProfissional>)allAreaProfissional.list();
		return areaProfissionalList;
	}
	

	public List<AreaProfissional> getAllForIndex() throws IntranetException{
		Criteria allAreaProfissionalForIndex = getSession().createCriteria(AreaProfissional.class);
		allAreaProfissionalForIndex.setMaxResults(5);
		allAreaProfissionalForIndex.addOrder(Order.desc("id"));	
		List<AreaProfissional> areaProfissionalList = allAreaProfissionalForIndex.list();
		return areaProfissionalList;
	}
	
	public AreaProfissional getAreaProfissionalById(Long id) throws IntranetException{
		Criteria areaProfissionalById = getSession().createCriteria(AreaProfissional.class);
		areaProfissionalById.add(Restrictions.eq("id", id));
		AreaProfissional areaProfissional = (AreaProfissional) areaProfissionalById.uniqueResult();
		return areaProfissional;
	}
	
	
	public List<AreaProfissional> getAllOfPessoa(Long id) throws IntranetException{
		Query queryVerify = getSession().getNamedQuery("allAreasByPessoa");
		queryVerify.setParameter("pessoa", id);
		return (List<AreaProfissional>) queryVerify.list();
	}
	

}
