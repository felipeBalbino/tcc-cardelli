package br.edu.gamaesouza.intranet.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.edu.gamaesouza.intranet.other.CustomSession;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public interface GenericDAO<T> {

	public void save(T t) throws IntranetException;
	
	public void update(T t) throws IntranetException;
	
	public void delete(T t) throws IntranetException;
	
	public T load(Integer id) throws IntranetException;

}
