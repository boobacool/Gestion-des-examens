package com.boobacool.notemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boobacool.notemanagement.models.Annee;
import com.boobacool.notemanagement.repositories.AnneeRepository;

@Service
public class AnneeDAO {
	
	@Autowired
	private AnneeRepository anneeRepository;
	
	public Annee save(Annee annee) {
		return anneeRepository.save(annee);
	}
	
	public List<Annee> findAll(){
		return anneeRepository.findAll();
	}
	
	
	
	public Annee findOne(Integer id) {
		return anneeRepository.getOne(id);
	}
	
	public void delete(Annee annee) {
		anneeRepository.delete(annee);
	}

}
