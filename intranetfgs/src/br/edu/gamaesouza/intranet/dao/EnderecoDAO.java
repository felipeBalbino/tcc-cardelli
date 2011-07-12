package br.edu.gamaesouza.intranet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.edu.gamaesouza.intranet.bean.Endereco;
import br.edu.gamaesouza.intranet.bean.Evento;
import br.edu.gamaesouza.intranet.bean.Pessoa;
import br.edu.gamaesouza.intranet.bean.Vaga;
import br.edu.gamaesouza.intranet.utils.IntranetException;

/**
 * @author Felipe Balbino
 * @since 02/06/2011
 */
public class EnderecoDAO extends HibernateDaoSupport {
	
	public void save(Endereco endereco) throws IntranetException{
		getHibernateTemplate().save(endereco);
	}
	
	public void update(Endereco endereco) throws IntranetException{	
		getHibernateTemplate().update(endereco);
	}
	
	public Endereco merge(Endereco endereco) throws IntranetException{
		return (Endereco)getHibernateTemplate().merge(endereco);
	}
	
	public void delete(Endereco endereco) throws IntranetException{
		getHibernateTemplate().delete(endereco);	
	}
	
	public List<Endereco> getAll() throws IntranetException{
		Criteria allEndereco = getSession().createCriteria(Endereco.class);
		List<Endereco> enderecoList = (List<Endereco>)allEndereco.list();
		return enderecoList;
	}
	
	public Endereco getEnderecoByid(Long enderecoId) throws IntranetException{
		Criteria c = getSession().createCriteria( Endereco.class );
		c.add( Restrictions.eq( "id", enderecoId ) );
		Endereco endereco = (Endereco) c.uniqueResult();
		return endereco;
	}
	



	

}
