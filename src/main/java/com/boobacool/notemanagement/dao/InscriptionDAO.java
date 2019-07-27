package com.boobacool.notemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boobacool.notemanagement.models.Annee;
import com.boobacool.notemanagement.models.Classe;
import com.boobacool.notemanagement.models.Inscription;
import com.boobacool.notemanagement.repositories.InscriptionRepository;

@Service
public class InscriptionDAO {
	
	@Autowired
	private InscriptionRepository inscriptionRepository;
	
	public Inscription save(Inscription inscription) {
		return inscriptionRepository.save(inscription);
	}
	
	public List<Inscription> findAll(){
		return inscriptionRepository.findAll();
	}
		
	public Inscription findOne(Integer id) {
		return inscriptionRepository.getOne(id);
	}
	
	public List<Inscription> findByClasseAndAnnee(Classe classe, Annee annee){
		return inscriptionRepository.findByClasseAndAnnee(classe, annee, Sort.by("etudiant.nom", "etudiant.prenom").ascending());
	}
	
	public void delete(Inscription inscription) {
		inscriptionRepository.delete(inscription);
	}

}
