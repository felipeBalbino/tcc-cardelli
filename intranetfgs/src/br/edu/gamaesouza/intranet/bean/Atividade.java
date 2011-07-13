package br.edu.gamaesouza.intranet.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class Atividade {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String nome;
	
	private Integer numeroHoras;

}
