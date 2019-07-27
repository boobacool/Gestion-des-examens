package com.boobacool.notemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boobacool.notemanagement.dao.AnneeDAO;
import com.boobacool.notemanagement.dao.AntenneDAO;
import com.boobacool.notemanagement.dao.ClasseDAO;
import com.boobacool.notemanagement.dao.FiliereDAO;
import com.boobacool.notemanagement.dao.InscriptionDAO;
import com.boobacool.notemanagement.dao.NiveauDAO;
import com.boobacool.notemanagement.models.Annee;
import com.boobacool.notemanagement.models.Classe;
import com.boobacool.notemanagement.models.Inscription;

@Controller
@RequestMapping("/inscription")
public class InscriptionController {
	
	@Autowired
	private InscriptionDAO inscriptionDAO;
	
	@Autowired
	private ClasseDAO classeDAO;
	
	@Autowired
	private AnneeDAO anneeDAO;
	
	@Autowired
	private FiliereDAO filiereDAO;
	
	@Autowired
	private NiveauDAO niveauDAO;
	
	@Autowired
	private AntenneDAO antenneDAO;
	
	
	@GetMapping("/formulaire")
	public String formulaire(Model model) {
		
		return "inscription";
	}
	
	@GetMapping("/inscriptions")
	public String accueilInscription(Model model, @RequestParam(name="idcla") Integer idcl, @RequestParam(name="ida") Integer ida) {	
		Inscription inscription = new Inscription();
		Classe classe = classeDAO.findOne(idcl);
		Annee annee = anneeDAO.findOne(ida);
		List<Inscription> listeInscription = inscriptionDAO.findByClasseAndAnnee(classe, annee);	
        model.addAttribute("listeInscription", listeInscription);
        model.addAttribute("inscription",inscription);
		return "inscription/inscriptions";	
	}
	
	

}
