package com.boobacool.notemanagement.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InsNoteListes implements Serializable {

	private static final long serialVersionUID = -2135426511499072524L;
	List<Notation> listeInsNotes = new ArrayList<>();

	public List<Notation> getListeInsNotes() {
		return listeInsNotes;
	}

	public void setListeInsNotes(List<Notation> listeInsNotes) {
		this.listeInsNotes = listeInsNotes;
	}

}
