package com.boobacool.notemanagement.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExaEpreuveListe implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<EpreuveCoef> listeEP = new ArrayList<>();
	
	private ExaEpreuve exaEpreuve;

	public ExaEpreuveListe() {
	}

	public ExaEpreuveListe(List<EpreuveCoef> listeEP, ExaEpreuve exaEpreuve) {
		super();
		this.listeEP = listeEP;
		this.exaEpreuve = exaEpreuve;
	}

	public List<EpreuveCoef> getListeEP() {
		return listeEP;
	}

	public void setListeEP(List<EpreuveCoef> listeEP) {
		this.listeEP = listeEP;
	}

	public ExaEpreuve getExaEpreuve() {
		return exaEpreuve;
	}

	public void setExaEpreuve(ExaEpreuve exaEpreuve) {
		this.exaEpreuve = exaEpreuve;
	}

	
	
}
