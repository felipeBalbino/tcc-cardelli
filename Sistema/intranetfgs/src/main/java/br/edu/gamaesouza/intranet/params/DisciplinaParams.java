package br.edu.gamaesouza.intranet.params;

import br.edu.gamaesouza.intranet.bean.Disciplina;
import br.edu.gamaesouza.intranet.utils.IntranetException;

public interface DisciplinaParams extends Params {
	Disciplina getDisciplina() throws IntranetException;
}
