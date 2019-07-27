package com.boobacool.notemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boobacool.notemanagement.models.Etudiant;
import com.boobacool.notemanagement.repositories.EtudiantRepository;

@Service
public class EtudiantDAO {
	
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	public Etudiant save(Etudiant etudiant) {
		return etudiantRepository.save(etudiant);
	}
	
	public List<Etudiant> findAll(){
		return etudiantRepository.findAll();
	}
	
	public Etudiant findOne(Integer id) {
		return etudiantRepository.getOne(id);
	}
	
	public void delete(Etudiant etudiant) {
		etudiantRepository.delete(etudiant);
	}

}
