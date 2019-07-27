package com.boobacool.notemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.boobacool.notemanagement.models.Epreuve;
import com.boobacool.notemanagement.repositories.EpreuveRepository;


@Service
public class EpreuveDAO {
	
	@Autowired
	private EpreuveRepository epreuveRepository;
	
	public Epreuve save(Epreuve epreuve) {
		return epreuveRepository.save(epreuve);
	}
	
	public List<Epreuve> findAll(){
		return epreuveRepository.findAll();
	}
	
	public Epreuve findOne(Integer id) {
		return epreuveRepository.getOne(id);
	}
	
	public void delete(Epreuve epreuve) {
		epreuveRepository.delete(epreuve);
	}
	public Epreuve findByLib(String lib) {
		return epreuveRepository.findByLib(lib);
	}

	
	public Page<Epreuve> listeEpreuves(String mc, Pageable pageable) {
		return epreuveRepository.chercherEpreuve(mc, pageable);
}
}
