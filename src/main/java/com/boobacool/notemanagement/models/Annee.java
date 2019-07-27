package com.boobacool.notemanagement.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="annee")
public class Annee {

	 @Id    
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Integer id;	    
	    private String lib;
		public Annee() {
			
			// TODO Auto-generated constructor stub
		}
		public Annee(String lib) {
			
			this.lib = lib;
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
		@Override
		public String toString() {
			return id + " " + lib;
		}
	    
}
