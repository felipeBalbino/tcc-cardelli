package br.edu.gamaesouza.intranet.bean;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

@Entity
@Table
public class Arquivo implements Serializable {

	@Transient
	private static final long serialVersionUID = 1976169200921487085L;


	@Id
	@GeneratedValue
	@Column(name="arq_id")
	private Integer id;


	@Column(name="arq_nome",nullable=false)
	private String nome;


	@Column(name="arq_url",nullable=false)
	private String url;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataEnvio;
	@Transient
	private File upload;   
	
	@Transient
    private String uploadFileName;
    
	@Transient
	private String uploadContentType;
	
	@OneToOne
	private DisciplinaLetiva disciplinaLetiva;
	
	@OneToOne
	private Professor professor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public DisciplinaLetiva getDisciplinaLetiva() {
		return disciplinaLetiva;
	}

	public void setDisciplinaLetiva(DisciplinaLetiva disciplinaLetiva) {
		this.disciplinaLetiva = disciplinaLetiva;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Calendar getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Calendar dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public void setUploadFileSize(long uploadFileSize) {
	}

	public long getUploadFileSize() {
		   ServletContext sContext = ServletActionContext.getServletContext();  
	 	   String diretorio = sContext.getRealPath("/arquivos");
	 	   File fds = new File(diretorio + "\\" +url); 
		   return fds.length();
	}
	
}
