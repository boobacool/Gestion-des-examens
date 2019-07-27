package com.boobacool.notemanagement.models;

import java.io.Serializable;

public class InsNote implements Serializable{
	
	private static final long serialVersionUID = -1523777017350147140L;
	private Inscription inscription;
	private Double note;
	public InsNote() {
		
		// TODO Auto-generated constructor stub
	}
	public InsNote(Inscription inscription, Double note) {
		
		this.inscription = inscription;
		this.note = note;
	}
	public Inscription getInscription() {
		return inscription;
	}
	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}
	public Double getNote() {
		return note;
	}
	public void setNote(Double note) {
		this.note = note;
	}
}
