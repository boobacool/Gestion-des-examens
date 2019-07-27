package com.boobacool.notemanagement.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Resultat{
	
	private static final long serialVersionUID = 5760621179698975687L;
	private Double moyenne;
	private String decision;
	private String nom;
	private String prenoms;
	private String conduite;
	private List<Notation> listeNotation = new ArrayList<>();
	
	public Resultat() {
		
	}

	
	public Resultat(Double moyenne, String decision, List<Notation> listeNotation) {
		this.moyenne = moyenne;
		this.decision = decision;
		this.listeNotation = listeNotation;
	}


	public String getDecision() {
		return decision;
	}


	public void setDecision(String decision) {
		this.decision = decision;
	}


	public Double getMoyenne() {
		return moyenne;
	}

	public void setMoyenne(Double moyenne) {
		this.moyenne = moyenne;
	}

	public List<Notation> getListeNotation() {
		return listeNotation;
	}

	public void setListeNotation(List<Notation> listeNotation) {
		this.listeNotation = listeNotation;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	


	public String getPrenoms() {
		return prenoms;
	}


	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}


	public String getConduite() {
		return conduite;
	}


	public void setConduite(String conduite) {
		this.conduite = conduite;
	}


	
	
}
