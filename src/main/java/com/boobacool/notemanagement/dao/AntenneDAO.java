package com.boobacool.notemanagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boobacool.notemanagement.models.Antenne;
import com.boobacool.notemanagement.repositories.AntenneRepository;

@Service
public class AntenneDAO {
	
	@Autowired
	private AntenneRepository antenneRepository;
	
	public Antenne save(Antenne antenne) {
		return antenneRepository.save(antenne);
	}
	
	public List<Antenne> findAll(){
		return antenneRepository.findAll();
	}
	
	
	
	public Antenne findOne(Integer id) {
		return antenneRepository.getOne(id);
	}
	
	public void delete(Antenne antenne) {
		antenneRepository.delete(antenne);
	}

}
