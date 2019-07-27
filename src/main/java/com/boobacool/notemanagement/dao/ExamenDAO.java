package com.boobacool.notemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.boobacool.notemanagement.models.Examen;
import com.boobacool.notemanagement.repositories.ExamenRepository;

@Service
public class ExamenDAO {
	
	@Autowired
	private ExamenRepository examenRepository;
	
	public Examen save(Examen examen) {
		return examenRepository.save(examen);
	}
	
	public List<Examen> findAll(){
		return examenRepository.findAll();
	}
	
	public Examen findByLib(String lib) {
		return examenRepository.findByLib(lib);
	}
	
	
	public Examen findOne(Integer id) {
		return examenRepository.getOne(id);
	}
	
	public void delete(Examen examen) {
		examenRepository.delete(examen);
	}
	
	public Page<Examen> listeExamens(String mc, Pageable pageable) {
		return examenRepository.chercherExamens(mc, pageable);
}

}
