package com.boobacool.notemanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boobacool.notemanagement.dao.AnneeDAO;
import com.boobacool.notemanagement.dao.AntenneDAO;
import com.boobacool.notemanagement.dao.ClasseDAO;
import com.boobacool.notemanagement.dao.EtudiantDAO;
import com.boobacool.notemanagement.dao.FiliereDAO;
import com.boobacool.notemanagement.dao.InscriptionDAO;
import com.boobacool.notemanagement.dao.NiveauDAO;
import com.boobacool.notemanagement.dao.NotationDAO;
import com.boobacool.notemanagement.models.Annee;
import com.boobacool.notemanagement.models.Antenne;
import com.boobacool.notemanagement.models.Classe;
import com.boobacool.notemanagement.models.Etudiant;
import com.boobacool.notemanagement.models.Filiere;
import com.boobacool.notemanagement.models.Inscription;
import com.boobacool.notemanagement.models.Niveau;
import com.boobacool.notemanagement.models.Notation;

@Controller
@RequestMapping("/etudiant")
public class EtudiantController {
	
	@Autowired
	private AnneeDAO anneeDAO;

	@Autowired
	private FiliereDAO filiereDAO;

	@Autowired
	private NiveauDAO niveauDAO;

	@Autowired
	private AntenneDAO antenneDAO;
	
	@Autowired
	private ClasseDAO classeDAO;
	
	@Autowired
	private InscriptionDAO inscriptionDAO;

	@Autowired
	private NotationDAO notationDAO;
	
	@Autowired
	private EtudiantDAO etudiantDAO;

	
	
	@GetMapping("/formulaire")
	public String formulaire(Model model) {

		model.addAttribute("listeAnnees", anneeDAO.findAll());
		model.addAttribute("listeAntennes", antenneDAO.findAll());
		model.addAttribute("listeNiveaux", niveauDAO.findAll());
		model.addAttribute("listeFilieres", filiereDAO.findAll());
		return "etudiant/etudiant";
	}
	
	@GetMapping("/formulaireAjout")
	public String formulaireAjout(Model model) {

		model.addAttribute("listeAnnees", anneeDAO.findAll());
		model.addAttribute("listeAntennes", antenneDAO.findAll());
		model.addAttribute("listeNiveaux", niveauDAO.findAll());
		model.addAttribute("listeFilieres", filiereDAO.findAll());
		model.addAttribute("etudiant", new Etudiant());
		return "etudiant/ajouterEtudiant";
	}
	
	@GetMapping("/findList")
	public String findOne(Model model, Integer ida, Integer idant, Integer idniv, Integer idfil) {

		if (ida == 0 || idant == 0 || idniv == 0 || idfil == 0) {
			model.addAttribute("erreur", "Tous les champs doivent être sélectionnés");
			return "/error/error";
		}

		Antenne ant = antenneDAO.findOne(idant);
		Annee an = anneeDAO.findOne(ida);
		Niveau niv = niveauDAO.findOne(idniv);
		Filiere fil = filiereDAO.findOne(idfil);

		Classe classe = null;
		classe = classeDAO.findByAntenneAndFiliereAndNiveau(ant, fil, niv);

		if (classe == null) {
			model.addAttribute("erreur", " la Classe est introuvable!!!!");
			return "/error/error";
		}

		model.addAttribute("ant", ant);
		model.addAttribute("an", an);
		model.addAttribute("cl", classe);

		List<Inscription> listeInscrires = inscriptionDAO.findByClasseAndAnnee(classe, an);
		model.addAttribute("listeEtudiants", listeInscrires);
		return "etudiant/listeEtudiants";
	}
	

	
	@PostMapping("/etudiantSave")
	public String saveEtudiant(Model model,@Valid Etudiant etudiant, Integer ida, Integer idant, Integer idniv, Integer idfil, String numins) {
	
		System.out.println(etudiant);
		
		
		if (ida == 0 || idant == 0 || idniv == 0 || idfil == 0) {
			model.addAttribute("erreur", "Tous les champs doivent être sélectionnés");
			return "/error/error";
		}
		
		etudiant = etudiantDAO.save(etudiant);
		

		Antenne ant = antenneDAO.findOne(idant);
		Annee an = anneeDAO.findOne(ida);
		Niveau niv = niveauDAO.findOne(idniv);
		Filiere fil = filiereDAO.findOne(idfil);

		Classe classe = null;
		classe = classeDAO.findByAntenneAndFiliereAndNiveau(ant, fil, niv);

		if (classe == null) {
			model.addAttribute("erreur", " la Classe est introuvable!!!!");
			return "/error/error";
		}
		
		Inscription insc = new Inscription(numins,0 , an, etudiant, classe);
		insc.setConduite("bonne");
		
		insc = inscriptionDAO.save(insc);

		model.addAttribute("ant", ant);
		model.addAttribute("an", an);
		model.addAttribute("cl", classe);

		List<Inscription> listeInscrires = inscriptionDAO.findByClasseAndAnnee(classe, an);
		model.addAttribute("listeEtudiants", listeInscrires);
		return "redirect:/etudiant/formulaireAjout";
	}
	
	
	
	@GetMapping("/change")
	public String change(Model model, Integer idins, Integer idcl,Integer ida) {
		
		Inscription inscription = inscriptionDAO.findOne(idins);
		model.addAttribute("listeAnnees", anneeDAO.findAll());
		model.addAttribute("listeAntennes", antenneDAO.findAll());
		model.addAttribute("listeNiveaux", niveauDAO.findAll());
		model.addAttribute("listeFilieres", filiereDAO.findAll());
		model.addAttribute("insc", inscription);		
		return "etudiant/change";
		
	}
	
	@GetMapping("/changeclasse")
	public String changeClasse(Model model, Integer idinsc, Integer idant, Integer idan, Integer idfil, Integer idniv,Integer ida, Integer idcla) {
		
		Inscription inscription = inscriptionDAO.findOne(idinsc);
		Classe classe = classeDAO.findByAntenneAndFiliereAndNiveau(antenneDAO.findOne(idant), filiereDAO.findOne(idfil), niveauDAO.findOne(idniv));
		if(inscription.getClasse().getId() != classe.getId()) {
			List<Notation> listeNotes = notationDAO.listeNotes(inscription);
			if(listeNotes.size() != 0) {
				for (int i = 0; i < listeNotes.size(); i++) {
					notationDAO.delete(listeNotes.get(i));
				}
			}
			
		     inscription.setClasse(classe);
		     inscriptionDAO.save(inscription);
			
		}
		
		
		return "redirect:formulaire";
	}


}
