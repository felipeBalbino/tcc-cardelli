package br.edu.gamaesouza.intranet.params;

import br.edu.gamaesouza.intranet.utils.IntranetException;
import br.edu.gamaesouza.intranet.vo.OuvidoriaVO;

public interface OuvidoriaParams extends Params {

	public OuvidoriaVO getData() throws IntranetException;
	
}
