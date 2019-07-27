package com.boobacool.notemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boobacool.notemanagement.models.Niveau;
import com.boobacool.notemanagement.repositories.NiveauRepository;

@Service
public class NiveauDAO {
	
	@Autowired
	private NiveauRepository niveauRepository;
	
	
	public Niveau save(Niveau niveau) {
		return niveauRepository.save(niveau);
	}
	
	public List<Niveau> findAll(){
		return niveauRepository.findAll();
	}
		
	public Niveau findOne(Integer id) {
		return niveauRepository.getOne(id);
	}
	
	public void delete(Niveau niveau) {
		niveauRepository.delete(niveau);
	}

}
