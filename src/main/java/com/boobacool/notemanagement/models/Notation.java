package com.boobacool.notemanagement.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notation")
public class Notation implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Double note;
	@ManyToOne
	@JoinColumn(name = "inscription")
	private Inscription inscription;
	@ManyToOne
	@JoinColumn(name = "exaepreuve")
	private ExaEpreuve exaEpreuve;
	
	public Notation() {
		
		// TODO Auto-generated constructor stub
	}
	public Notation(Double note, Inscription inscription, ExaEpreuve exaEpreuve) {
		
		this.note = note;
		this.inscription = inscription;
		this.exaEpreuve = exaEpreuve;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getNote() {
		return note;
	}
	public void setNote(Double note) {
		this.note = note;
	}
	public Inscription getInscription() {
		return inscription;
	}
	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}
	public ExaEpreuve getExaEpreuve() {
		return exaEpreuve;
	}
	public void setExaEpreuve(ExaEpreuve exaEpreuve) {
		this.exaEpreuve = exaEpreuve;
	}
	
	
}
