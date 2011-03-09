package br.edu.gamaesouza.intranet.taglib;

import java.util.List;

import br.edu.gamaesouza.intranet.bean.Curso;
import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.bean.Rule;

public class IntranetCoreTags {
	
	public static String contains(List<Rule> lista,Object object){
		
		
		for (Rule rule : lista){
			if (rule.getNome().equals(object.toString())){
				return "selected";
			}
		}
		
		return null;
		
	}
	
public static String containsDisciplina(List<Disciplina> lista,Object object){
		
		
		for (Disciplina rule : lista){
			if (rule != null && object != null){
				if (rule.getNome().equals(object.toString())){
					return "selected";
				}
			}
		}
		
		return null;
		
	}

public static String containsCurso(List<Curso> lista,Object object){
	
	
	for (Curso rule : lista){
		if (rule != null && object != null){
		if (rule.getNome().equals(object.toString())){
			return "selected";
		}
		}
	}
	
	return null;
	
}
	
}
