package com.boobacool.notemanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boobacool.notemanagement.models.ExaEpreuve;
import com.boobacool.notemanagement.models.Inscription;
import com.boobacool.notemanagement.models.Notation;

public interface NotationRepository extends JpaRepository<Notation, Integer>{
 public Notation findByInscriptionAndExaEpreuve(Inscription inscription, ExaEpreuve exaEpreuve);
 public List<Notation> findByInscription(Inscription inscription);
 public List<Notation> findByExaEpreuve(ExaEpreuve exaEpreuve);
}
