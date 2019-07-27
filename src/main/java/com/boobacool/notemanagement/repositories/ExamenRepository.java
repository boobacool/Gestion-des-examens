package com.boobacool.notemanagement.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.boobacool.notemanagement.models.Examen;

public interface ExamenRepository extends JpaRepository<Examen, Integer>{
	
	@Query("select e from Examen e where e.lib like :x")
	public Page<Examen> chercherExamens(@Param("x")String mc, Pageable pageable);
	
	public Examen findByLib(String lib);

}
