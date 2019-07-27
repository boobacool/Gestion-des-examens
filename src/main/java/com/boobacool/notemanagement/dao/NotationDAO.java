package com.boobacool.notemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boobacool.notemanagement.models.ExaEpreuve;
import com.boobacool.notemanagement.models.Inscription;
import com.boobacool.notemanagement.models.Notation;
import com.boobacool.notemanagement.repositories.NotationRepository;

@Service
public class NotationDAO {
	
	@Autowired
	private NotationRepository notationRepository;
	
	
	public Notation save(Notation notation) {
		return notationRepository.save(notation);
	}
	
	public List<Notation> findAll(){
		return notationRepository.findAll();
	}
		
	public Notation findOne(Integer id) {
		return notationRepository.getOne(id);
	}
	
	public void delete(Notation notation) {
		notationRepository.delete(notation);
	}
	
	public Notation findByInscriptionAndExaEpreuve(Inscription inscription, ExaEpreuve exaEpreuve) {
		return notationRepository.findByInscriptionAndExaEpreuve(inscription, exaEpreuve);
	}
	
	public List<Notation> listeNotes(Inscription inscription){
		return notationRepository.findByInscription(inscription);
	}
	
	 public List<Notation> findByExaEpreuve(ExaEpreuve exaEpreuve ){
		 return notationRepository.findByExaEpreuve(exaEpreuve);
	 }

}
