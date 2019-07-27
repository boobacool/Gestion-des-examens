package com.boobacool.notemanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boobacool.notemanagement.dao.AnneeDAO;
import com.boobacool.notemanagement.dao.AntenneDAO;
import com.boobacool.notemanagement.dao.ClasseDAO;
import com.boobacool.notemanagement.dao.EpreuveDAO;
import com.boobacool.notemanagement.dao.ExaEpreuveDAO;
import com.boobacool.notemanagement.dao.ExamenDAO;
import com.boobacool.notemanagement.dao.FiliereDAO;
import com.boobacool.notemanagement.dao.InscriptionDAO;
import com.boobacool.notemanagement.dao.NiveauDAO;
import com.boobacool.notemanagement.dao.NotationDAO;
import com.boobacool.notemanagement.models.Annee;
import com.boobacool.notemanagement.models.Antenne;
import com.boobacool.notemanagement.models.Classe;
import com.boobacool.notemanagement.models.ExaEpreuve;
import com.boobacool.notemanagement.models.Examen;
import com.boobacool.notemanagement.models.Filiere;
import com.boobacool.notemanagement.models.Inscription;
import com.boobacool.notemanagement.models.Niveau;
import com.boobacool.notemanagement.models.Notation;
import com.boobacool.notemanagement.models.Resultat;
import com.boobacool.notemanagement.models.ResultatList;

@Controller
@RequestMapping("/resultat")
public class ResultatController {

	@Autowired
	private AnneeDAO anneeDAO;

	@Autowired
	private FiliereDAO filiereDAO;

	@Autowired
	private NiveauDAO niveauDAO;

	@Autowired
	private AntenneDAO antenneDAO;

	@Autowired
	private ExamenDAO examenDAO;

	@Autowired
	private ClasseDAO classeDAO;

	@Autowired
	private ExaEpreuveDAO exaEpreuveDAO;

	@Autowired
	private EpreuveDAO epreuveDAO;

	@Autowired
	private InscriptionDAO inscriptionDAO;

	@Autowired
	private NotationDAO notationDAO;


	@GetMapping("/formulaire")
	public String formulaire(Model model) {

		model.addAttribute("listeAnnees", anneeDAO.findAll());
		model.addAttribute("listeAntennes", antenneDAO.findAll());
		model.addAttribute("listeNiveaux", niveauDAO.findAll());
		model.addAttribute("listeFilieres", filiereDAO.findAll());
		model.addAttribute("listeExamens", examenDAO.findAll());
		return "resultat/resultat";
	}

	@GetMapping("/findList")
	public String findOne(Model model, Integer ida, Integer idant, Integer idniv, Integer idfil, Integer idexa) {

		if (ida == 0 || idant == 0 || idniv == 0 || idfil == 0 || idexa == 0) {
			model.addAttribute("erreur", "Tous les champs doivent être sélectionnés");
			return "/error/error";
		}

		Antenne ant = antenneDAO.findOne(idant);
		Examen exa = examenDAO.findOne(idexa);
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
		model.addAttribute("exa", exa);

		List<Inscription> listeInscrires = inscriptionDAO.findByClasseAndAnnee(classe, an);
		List<ExaEpreuve> listeepreuves = exaEpreuveDAO.findByAnneeAndClasseAndAndExamen(an, classe, exa);

		ResultatList resultatList = new ResultatList(classe, an, listeepreuves);
		
		model.addAttribute("nombre", listeepreuves.size());

		if (listeInscrires.size() == 0 || listeepreuves.size() == 0) {
			model.addAttribute("erreur", "cette classe est vide ou la liste de matières est vide");
			return "/error/error";
		} else {
			for (int i = 0; i < listeInscrires.size(); i++) {
				List<Notation> listeNotation = new ArrayList<>();
				Double somme = 0.0;
				Integer coef = 0;
				Resultat resultat = new Resultat();
				resultat.setNom(listeInscrires.get(i).getEtudiant().getNom());
				resultat.setPrenoms(listeInscrires.get(i).getEtudiant().getPrenom());
				resultat.setConduite(listeInscrires.get(i).getConduite());
				for (int j = 0; j < listeepreuves.size(); j++) {
					Notation nota = new Notation();
					nota = notationDAO.findByInscriptionAndExaEpreuve(listeInscrires.get(i), listeepreuves.get(j));
					if (nota == null) {
						Notation nota1 = new Notation();
						nota1.setExaEpreuve(listeepreuves.get(j));
						nota1.setInscription(listeInscrires.get(i));
						nota1.setNote(0.0);
						resultat.getListeNotation().add(nota1);
						somme = somme + (nota1.getNote() * nota1.getExaEpreuve().getCoef());
						coef = coef + nota1.getExaEpreuve().getCoef();
						listeNotation.add(nota1);
					}else {
						resultat.getListeNotation().add(nota);
						somme = somme + (nota.getNote() * nota.getExaEpreuve().getCoef());
						coef = coef + nota.getExaEpreuve().getCoef();
						listeNotation.add(nota);
					}
					
					
				}
				resultat.setMoyenne(somme / coef);
				resultat.setDecision(appreciation(somme/coef));
				resultat.setListeNotation(listeNotation);
				resultatList.getListeResultats().add(resultat);

			}
			
			
		}

		model.addAttribute("resultatList", resultatList);
		return "resultat/resultats";
	}
	
	
	
	public  String appreciation(Double moyenne) {
		
		if(moyenne < 6) 
			return "Très Failble";
		else if(moyenne >=6 && moyenne <9.5)
			return "Faible";
		else if(moyenne >=9.5 && moyenne <10)
		   return "Insuffisant";
		else if(moyenne >=10 && moyenne <12)
			return "Passable";
		else if(moyenne >=12 && moyenne <13)
			return "Bien";
		else if(moyenne >=13 && moyenne <15)
			return "Assez-Bien";
		else if(moyenne >=15 && moyenne <17)
			return "Très-Bien";
		else
			return "Excellent";
	}
}

