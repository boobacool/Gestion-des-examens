package com.boobacool.notemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boobacool.notemanagement.models.Antenne;
import com.boobacool.notemanagement.models.Classe;
import com.boobacool.notemanagement.models.Filiere;
import com.boobacool.notemanagement.models.Niveau;
import com.boobacool.notemanagement.repositories.ClasseRepository;

@Service
public class ClasseDAO {
	
	@Autowired
	private ClasseRepository classeRepository;
	
	public Classe save(Classe classe) {
		return classeRepository.save(classe);
	}
	
	public List<Classe> findAll(){
		return classeRepository.findAll();
	}
	
	
	public Classe findOne(Integer id) {
		return classeRepository.getOne(id);
	}
	
	public void delete(Classe classe) {
		classeRepository.delete(classe);
	}
	
	public Classe findByAntenneAndFiliereAndNiveau(Antenne antenne, Filiere filiere, Niveau niveau) {
		return classeRepository.findByAntenneAndFiliereAndNiveau(antenne, filiere, niveau);
	}
	
	public List<Classe> findByAntenne(Antenne antenne) {
		return classeRepository.findByAntenne(antenne);
	}
	

}
