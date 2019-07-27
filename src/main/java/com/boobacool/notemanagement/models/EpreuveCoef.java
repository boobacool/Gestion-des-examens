package com.boobacool.notemanagement.models;

import java.io.Serializable;

public class EpreuveCoef implements Serializable{
	
	
	public Epreuve getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}

	public Integer getCoef() {
		return coef;
	}

	public void setCoef(Integer coef) {
		this.coef = coef;
	}

	private static final long serialVersionUID = 5326655975381777761L;
	private Epreuve epreuve;
	private Integer coef;
	
	public EpreuveCoef() {
	
	}

	public EpreuveCoef(Epreuve epreuve, Integer coef) {
		this.epreuve = epreuve;
		this.coef = coef;
	}
	
	

}
