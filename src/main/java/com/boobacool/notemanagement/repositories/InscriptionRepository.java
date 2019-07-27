package com.boobacool.notemanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import com.boobacool.notemanagement.models.Annee;
import com.boobacool.notemanagement.models.Classe;
import com.boobacool.notemanagement.models.Inscription;

public interface InscriptionRepository extends JpaRepository<Inscription, Integer>{

	public Inscription findByNumins(String numins);
	
	public List<Inscription> findByClasseAndAnnee(Classe classe, Annee annee,Sort sort);
	
}
