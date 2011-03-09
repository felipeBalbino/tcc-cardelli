package br.edu.gamaesouza.intranet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.edu.gamaesouza.intranet.bean.Arquivo;
import br.edu.gamaesouza.intranet.bean.Professor;
import br.edu.gamaesouza.intranet.other.CustomSession;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public class ArquivoDAO extends GenericDAO<Arquivo> {
	
	private Session session;
	private Transaction transaction;

	
	public void save(Arquivo t) throws IntranetException{
		session = CustomSession.getSession();
		transaction = session.beginTransaction();
		session.save(t);
		transaction.commit();
		this.session.close();
		
	}
	
	public List<Arquivo> getArquivos(Integer materia,Integer ano, Integer semestre, Integer professor) throws IntranetException{
		session = CustomSession.getSession();
		
		// && turno == -1
		if (ano == -1 && semestre == -1 && materia == -1 && professor == -1 ){
			Criteria c = session.createCriteria(Arquivo.class);
			List<Arquivo> list = (List<Arquivo>)c.list();
			return list;
		}
		
		boolean first = true;
		
		String hqlQuery = "SELECT arquivo FROM Arquivo arquivo, IN(arquivo.disciplinaLetiva.disciplina.cursos) curso WHERE ";
		
		
		
		if(ano != -1){
			if(first){
				hqlQuery = hqlQuery + "arquivo.disciplinaLetiva.ano = " + ano;
				first = false;
			}else{
				hqlQuery = hqlQuery + " AND arquivo.disciplinaLetiva.ano = " + ano;
			}
		}
		
		if(semestre != -1){
			if(first){
				hqlQuery = hqlQuery + "arquivo.disciplinaLetiva.semestre = " + semestre;
				first = false;
			}else{
				hqlQuery = hqlQuery + " AND arquivo.disciplinaLetiva.semestre = " + semestre;
			}
		}
		
		
		if(materia != -1){
			if(first){
				hqlQuery = hqlQuery + "arquivo.disciplinaLetiva.disciplina.id = " + materia;
				first = false;
			}else{
				hqlQuery = hqlQuery + " AND arquivo.disciplinaLetiva.disciplina.id = " + materia;
			}
		}
		
		if(professor != -1){
			if(first){
				hqlQuery = hqlQuery + "arquivo.professor.id = " + professor;
				first = false;
			}else{
				hqlQuery = hqlQuery + " AND arquivo.professor.id = " + professor;
			}
		}	
		
		/*if(turno != -1){
			if(first){
				hqlQuery = hqlQuery + "arquivo.disciplinaLetiva.turno = " + turno;
				first = false;
			}else{
				hqlQuery = hqlQuery + " AND arquivo.disciplinaLetiva.turno = " + turno;
			}
		}
		*/
			
		Query query = session.createQuery(hqlQuery);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Arquivo> list = query.list();
		this.session.close();
		return list;
		
		
	}

	public List<Arquivo> getArquivos(Professor object) throws IntranetException {
		session = CustomSession.getSession();
		Criteria c = session.createCriteria(Arquivo.class);
		c.createCriteria("professor").add(Restrictions.eq("nome", object.getNome()));
		c.addOrder(Order.desc("id"));
		List<Arquivo> result = c.list();
		this.session.close();
		return result;
	}

	public void delete(Integer id, Professor professor) throws IntranetException {
		
		
		session = CustomSession.getSession();
		Criteria c = session.createCriteria(Arquivo.class);
		c.add(Restrictions.eq("id", id));
		c.createCriteria("professor").add
			(Restrictions.eq("nome", professor.getNome()));
		
		Arquivo arquivotemp = (Arquivo) c.uniqueResult();
		
		if(arquivotemp == null){
			throw new IntranetException("ArquivoTemp vazio");	
		}else{
			session.delete(arquivotemp);
			transaction = session.beginTransaction();
			transaction.commit();
		}
		
		this.session.close();
		
	}

	public Arquivo getArquivoById(Integer id) throws IntranetException {
		session = CustomSession.getSession();
		Criteria c = session.createCriteria(Arquivo.class);
		c.add(Restrictions.eq("id", id));
		Arquivo arquivo = (Arquivo) c.uniqueResult();
		this.session.close();
		return arquivo;
		
	}
	
	public Arquivo alter(Integer id, Professor professor) throws IntranetException {
		session = CustomSession.getSession();
		Criteria c = session.createCriteria(Arquivo.class);
		c.add(Restrictions.eq("id", id));
		c.createCriteria("professor").add
			(Restrictions.eq("nome", professor.getNome()));
		
		Arquivo arquivotemp = (Arquivo) c.uniqueResult();
		this.session.close();
		if(arquivotemp == null){
			throw new IntranetException("ArquivoTemp Vazio");	
		}else{
			return arquivotemp;
		}
		
	}
	
}
