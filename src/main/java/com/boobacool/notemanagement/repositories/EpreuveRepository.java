package com.boobacool.notemanagement.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boobacool.notemanagement.models.Epreuve;

public interface EpreuveRepository extends JpaRepository<Epreuve, Integer>{

	@Query("select e from Epreuve e where e.lib like :x")
	public Page<Epreuve> chercherEpreuve(@Param("x")String mc, Pageable pageable);
	
	public Epreuve findByLib(String lib);
}
