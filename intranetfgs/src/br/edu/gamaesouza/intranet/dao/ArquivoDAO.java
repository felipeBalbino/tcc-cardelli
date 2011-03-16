package br.edu.gamaesouza.intranet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.edu.gamaesouza.intranet.bean.Arquivo;
import br.edu.gamaesouza.intranet.bean.Professor;
import br.edu.gamaesouza.intranet.utils.IntranetException;

/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2010
 */
public class ArquivoDAO extends HibernateDaoSupport {
	
	public void save(Arquivo t) throws IntranetException{	
		getHibernateTemplate().save(t);	
	}
	
	public List<Arquivo> getArquivos(Integer materia,Integer ano, Integer semestre, Integer professor) throws IntranetException{
		if (ano == -1 && semestre == -1 && materia == -1 && professor == -1 ){
			Criteria allArquivos;
			allArquivos = getSession().createCriteria(Arquivo.class);
			List<Arquivo> listaTodosArquivos = (List<Arquivo>)allArquivos.list();
			return listaTodosArquivos;
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
					
		Query query = getSession().createQuery(hqlQuery);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		List<Arquivo> listaArquivosComParam = query.list();
		return listaArquivosComParam;
			
	}

	public List<Arquivo> getArquivos(Professor object) throws IntranetException {
		Criteria arquivosProfessor = getSession().createCriteria(Arquivo.class);
		arquivosProfessor.createCriteria("professor").add(Restrictions.eq("nome", object.getNome()));
		arquivosProfessor.addOrder(Order.desc("id"));
		List<Arquivo> listaArquivosPorProfessor = arquivosProfessor.list();
		return listaArquivosPorProfessor;
	}

	public void delete(Integer id, Professor professor) throws IntranetException {
		Criteria arquivoProfessorDeleta = getSession().createCriteria(Arquivo.class);
		arquivoProfessorDeleta.add(Restrictions.eq("id", id));
		arquivoProfessorDeleta.createCriteria("professor").add
			(Restrictions.eq("nome", professor.getNome()));
		
		Arquivo arquivoTemporarioDeleta = (Arquivo) arquivoProfessorDeleta.uniqueResult();
		
		if(arquivoTemporarioDeleta == null){
			//TODO Ocorreu um erro, tratar
		}else{
			getHibernateTemplate().delete(arquivoTemporarioDeleta);
		}
		
	}

	public Arquivo getArquivoById(Integer id) throws IntranetException {
		Criteria arquivoById = getSession().createCriteria(Arquivo.class);
		arquivoById.add(Restrictions.eq("id", id));
		Arquivo arquivo = (Arquivo) arquivoById.uniqueResult();
		return arquivo;
	}
	
}
