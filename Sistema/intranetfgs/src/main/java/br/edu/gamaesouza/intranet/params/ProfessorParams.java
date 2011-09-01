package br.edu.gamaesouza.intranet.params;

import br.edu.gamaesouza.intranet.bean.Professor;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public interface ProfessorParams extends Params {

	public Professor getProfessor() throws IntranetException;
	
}
