package br.edu.gamaesouza.intranet.bean;

import java.io.File;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.servlet.ServletContext;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.apache.struts2.ServletActionContext;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * @author Gabriel Cardelli
 * @author Felipe Balbino
 * @since 15/03/2011
 */
@Entity
@NamedQueries(value={
		@NamedQuery(name="VaziaArquivo",query="FROM Arquivo")
})
public class Arquivo implements Serializable {

	@Transient
	private static final long serialVersionUID = 1976169200921487085L;


	@Id
	@GeneratedValue
	@Column(name="arq_id")
	@Getter @Setter
	private Integer id;


	@Column(name="arq_nome")
	@Getter @Setter
	@NotEmpty
	private String nome;


	@Column(name="arq_url")
	@Getter @Setter
	@NotEmpty
	private String url;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter @Setter
	@NotEmpty
	private Calendar dataEnvio;
	
	@Transient
	@Getter @Setter
	private File upload;   
	
	@Transient
	@Getter @Setter
    private String uploadFileName;
	
	@Transient
	@Setter
    private String uploadFileSize;
    
	@Transient
	@Getter @Setter
	private String uploadContentType;
	
	@OneToOne
	@Getter @Setter
	private DisciplinaLetiva disciplinaLetiva;
	
	@OneToOne
	@Getter @Setter
	private Professor professor;

	
	public String getUploadFileSize() {
	    double BASE = 1024, KB = BASE, MB = KB*BASE, GB = MB*BASE;
	    DecimalFormat df = new DecimalFormat("#.##");
	    
	    ServletContext sContext = ServletActionContext.getServletContext();  
		String diretorio = sContext.getRealPath("/arquivos");
		File fds = new File(diretorio + "\\" +url); 
	    	
	    if(fds.length() >= GB) {
	         return df.format(fds.length()/GB) + " GB";
	    }
	    if(fds.length() >= MB) {
	         return df.format(fds.length()/MB) + " MB";
	    }
	    if(fds.length() >= KB) {
	         return df.format(fds.length()/KB) + " KB";
	    }
	    	return "" + (int)fds.length() + " bytes";
		}
	
}
