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
@Table(name = "inscription")
public class Inscription implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String numins;
	private int etat;
	private String conduite;
	@ManyToOne
	@JoinColumn(name = "annee")
	private Annee annee;
	@ManyToOne
	@JoinColumn(name = "etudiant")
	private Etudiant etudiant;
	@ManyToOne
	@JoinColumn(name = "classe")
	private Classe classe;

	public Inscription() {

		// TODO Auto-generated constructor stub
	}

	public Inscription(String numins, int etat, Annee annee, Etudiant etudiant, Classe classe) {

		this.numins = numins;
		this.etat = etat;
		this.annee = annee;
		this.etudiant = etudiant;
		this.classe = classe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumins() {
		return numins;
	}

	public void setNumins(String numins) {
		this.numins = numins;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public Annee getAnnee() {
		return annee;
	}

	public void setAnnee(Annee annee) {
		this.annee = annee;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public String getConduite() {
		return conduite;
	}

	public void setConduite(String conduite) {
		this.conduite = conduite;
	}
}
