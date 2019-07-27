package com.boobacool.notemanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boobacool.notemanagement.models.Annee;
import com.boobacool.notemanagement.models.Classe;
import com.boobacool.notemanagement.models.Epreuve;
import com.boobacool.notemanagement.models.ExaEpreuve;
import com.boobacool.notemanagement.models.Examen;


public interface ExaEpreuveRepository extends JpaRepository<ExaEpreuve, Integer>{

	public ExaEpreuve findByAnneeAndClasseAndEpreuveAndExamen(Annee annee, Classe classe, Epreuve epreuve, Examen examen);
	public List<ExaEpreuve> findByAnneeAndClasseAndAndExamen(Annee annee, Classe classe,Examen examen);
	
}
