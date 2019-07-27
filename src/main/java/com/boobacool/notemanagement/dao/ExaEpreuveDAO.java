package com.boobacool.notemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boobacool.notemanagement.models.Annee;
import com.boobacool.notemanagement.models.Classe;
import com.boobacool.notemanagement.models.Epreuve;
import com.boobacool.notemanagement.models.ExaEpreuve;
import com.boobacool.notemanagement.models.Examen;
import com.boobacool.notemanagement.repositories.ExaEpreuveRepository;

@Service
public class ExaEpreuveDAO {
	
	@Autowired
	private ExaEpreuveRepository exaEpreuveRepository;
	
	
	public ExaEpreuve save(ExaEpreuve exaEpreuve) {
		return exaEpreuveRepository.save(exaEpreuve);
	}
	
	public List<ExaEpreuve> findAll(){
		return exaEpreuveRepository.findAll();
	}
	
	
	
	public ExaEpreuve findOne(Integer id) {
		return exaEpreuveRepository.getOne(id);
	}
	
	public void delete(ExaEpreuve exaEpreuve) {
		exaEpreuveRepository.delete(exaEpreuve);
	}
	
	public ExaEpreuve findByAnneeAndClasseAndEpreuveAndExamen(Annee annee, Classe classe, Epreuve epreuve, Examen examen) {
		return exaEpreuveRepository.findByAnneeAndClasseAndEpreuveAndExamen(annee, classe, epreuve, examen);
	}
	
	public List<ExaEpreuve> findByAnneeAndClasseAndAndExamen(Annee annee, Classe classe, Examen examen) {
		return exaEpreuveRepository.findByAnneeAndClasseAndAndExamen(annee, classe,examen);
	}
	

}
