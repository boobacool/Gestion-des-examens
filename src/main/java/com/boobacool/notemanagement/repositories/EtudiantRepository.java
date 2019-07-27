package com.boobacool.notemanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boobacool.notemanagement.models.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer>{

}
