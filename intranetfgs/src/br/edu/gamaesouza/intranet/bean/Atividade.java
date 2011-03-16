package br.edu.gamaesouza.intranet.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Atividade {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String nome;
	
	private Integer numeroHoras;

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

	public Integer getNumeroHoras() {
		return numeroHoras;
	}

	public void setNumeroHoras(Integer numeroHoras) {
		this.numeroHoras = numeroHoras;
	}
	
}
