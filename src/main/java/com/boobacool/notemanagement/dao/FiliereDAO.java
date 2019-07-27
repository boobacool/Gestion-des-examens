package com.boobacool.notemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boobacool.notemanagement.models.Filiere;
import com.boobacool.notemanagement.repositories.FiliereRepository;

@Service
public class FiliereDAO {
	
	@Autowired
	private FiliereRepository filiereRepository;
	
	public Filiere save(Filiere filiere) {
		return filiereRepository.save(filiere);
	}
	
	public List<Filiere> findAll(){
		return filiereRepository.findAll();
	}
		
	public Filiere findOne(Integer id) {
		return filiereRepository.getOne(id);
	}
	
	public void delete(Filiere filiere) {
		filiereRepository.delete(filiere);
	}

}
