package com.boobacool.notemanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boobacool.notemanagement.models.Antenne;
import com.boobacool.notemanagement.models.Classe;
import com.boobacool.notemanagement.models.Filiere;
import com.boobacool.notemanagement.models.Niveau;

public interface ClasseRepository extends JpaRepository<Classe, Integer>{
	
	public Classe findByAntenneAndFiliereAndNiveau(Antenne antenne, Filiere filiere, Niveau niveau);
	public List<Classe> findByAntenne(Antenne antenne);
}
