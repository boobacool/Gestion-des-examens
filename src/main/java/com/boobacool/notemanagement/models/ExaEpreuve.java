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
@Table(name = "exaepreuve")
public class ExaEpreuve implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer coef;
	@ManyToOne
	@JoinColumn(name = "examen")
	private Examen examen;
	@ManyToOne
	@JoinColumn(name = "classe")
	private Classe classe;
	@ManyToOne
	@JoinColumn(name = "annee")
	private Annee annee;
	@ManyToOne
	@JoinColumn(name = "epreuve")
	private Epreuve epreuve;
	
	public ExaEpreuve() {
		
		// TODO Auto-generated constructor stub
	}
	
	public ExaEpreuve(Integer coef, Examen examen, Classe classe, Annee annee, Epreuve epreuve) {
		super();
		this.coef = coef;
		this.examen = examen;
		this.classe = classe;
		this.annee = annee;
		this.epreuve = epreuve;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCoef() {
		return coef;
	}
	public void setCoef(Integer coef) {
		this.coef = coef;
	}
	public Examen getExamen() {
		return examen;
	}
	public void setExamen(Examen examen) {
		this.examen = examen;
	}
	public Classe getClasse() {
		return classe;
	}
	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	public Annee getAnnee() {
		return annee;
	}
	public void setAnnee(Annee annee) {
		this.annee = annee;
	}
		
	public Epreuve getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}

	@Override
	public String toString() {
		return id + " " + coef + " " + examen + " " + classe + " "
				+ annee;
	}
	
	

}
