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
@Table(name = "classe")
public class Classe implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String lib;
	@ManyToOne
	@JoinColumn(name = "filiere")
	private Filiere filiere;
	@ManyToOne
	@JoinColumn(name = "niveau")
	private Niveau niveau;
	@ManyToOne
	@JoinColumn(name = "antenne")
	private Antenne antenne;

	public Classe() {
		
		// TODO Auto-generated constructor stub
	}

	public Classe(String lib, Filiere filiere, Niveau niveau, Antenne antenne) {
		
		this.lib = lib;
		this.filiere = filiere;
		this.niveau = niveau;
		this.antenne = antenne;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLib() {
		return lib;
	}

	public void setLib(String lib) {
		this.lib = lib;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public Antenne getAntenne() {
		return antenne;
	}

	public void setAntenne(Antenne antenne) {
		this.antenne = antenne;
	}

}
