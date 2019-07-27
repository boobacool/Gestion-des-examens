package com.boobacool.notemanagement.models;

import java.util.ArrayList;
import java.util.List;

public class ResultatList {
	
	private Classe classe;
	private Annee annee;
	private List<Resultat> listeResultats = new ArrayList<>();
	private List<ExaEpreuve> listeMatieres = new ArrayList<>();
	
	public ResultatList() {
		
	}

	public ResultatList(Classe classe, Annee annee) {
		this.classe = classe;
		this.annee = annee;
	}
	
	

	public ResultatList(Classe classe, Annee annee, List<ExaEpreuve> listeMatieres) {
		this.classe = classe;
		this.annee = annee;
		this.listeMatieres = listeMatieres;
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

	public List<Resultat> getListeResultats() {
		return listeResultats;
	}

	public void setListeResultats(List<Resultat> listeResultats) {
		this.listeResultats = listeResultats;
	}

	public List<ExaEpreuve> getListeMatieres() {
		return listeMatieres;
	}

	public void setListeMatieres(List<ExaEpreuve> listeMatieres) {
		this.listeMatieres = listeMatieres;
	}

}
