package br.edu.gamaesouza.intranet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.edu.gamaesouza.intranet.bean.Empresa;
import br.edu.gamaesouza.intranet.bean.Evento;
import br.edu.gamaesouza.intranet.bean.Vaga;
import br.edu.gamaesouza.intranet.utils.IntranetException;

/**
 * @author Felipe Balbino
 * @since 02/06/2011
 */
public class EmpresaDAO extends HibernateDaoSupport {
	
	public void save(Empresa empresa) throws IntranetException{
		getHibernateTemplate().save(empresa);
	}
	
	public void update(Empresa empresa) throws IntranetException{	
		getHibernateTemplate().update(empresa);
	}
	
	public void merge(Empresa empresa) throws IntranetException{
		getHibernateTemplate().merge(empresa);
	}
	
	
	public void delete(Empresa empresa) throws IntranetException{
		getHibernateTemplate().delete(empresa);	
	}
	
	public List<Empresa> getAll() throws IntranetException{
		Criteria allEmpresa = getSession().createCriteria(Empresa.class);
		List<Empresa> empresaList = (List<Empresa>)allEmpresa.list();
		return empresaList;
	}
	

	public List<Empresa> getAllForIndex() throws IntranetException{
		Criteria allEmpresaForIndex = getSession().createCriteria(Empresa.class);
		allEmpresaForIndex.setMaxResults(5);
		allEmpresaForIndex.addOrder(Order.desc("id"));	
		List<Empresa> empresaList = allEmpresaForIndex.list();
		return empresaList;
	}
	
	public Empresa getEmpresaById(Integer id) throws IntranetException{
		Criteria empresaById = getSession().createCriteria(Empresa.class);
		empresaById.add(Restrictions.eq("id", id));
		Empresa empresa = (Empresa) empresaById.uniqueResult();
		return empresa;
	}
	

}
