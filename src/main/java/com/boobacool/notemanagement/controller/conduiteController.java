package com.boobacool.notemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boobacool.notemanagement.dao.AnneeDAO;
import com.boobacool.notemanagement.dao.AntenneDAO;
import com.boobacool.notemanagement.dao.ClasseDAO;
import com.boobacool.notemanagement.dao.ExamenDAO;
import com.boobacool.notemanagement.dao.FiliereDAO;
import com.boobacool.notemanagement.dao.InscriptionDAO;
import com.boobacool.notemanagement.dao.NiveauDAO;
import com.boobacool.notemanagement.models.Annee;
import com.boobacool.notemanagement.models.Antenne;
import com.boobacool.notemanagement.models.Classe;
import com.boobacool.notemanagement.models.Examen;
import com.boobacool.notemanagement.models.Filiere;
import com.boobacool.notemanagement.models.Inscription;
import com.boobacool.notemanagement.models.Niveau;

@Controller
@RequestMapping("/conduite")
public class conduiteController{

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
	private InscriptionDAO inscriptionDAO;

	@GetMapping("/formulaire")
	public String supprimerMatiere(Model model) {

		model.addAttribute("listeAnnees", anneeDAO.findAll());
		model.addAttribute("listeAntennes", antenneDAO.findAll());
		model.addAttribute("listeNiveaux", niveauDAO.findAll());
		model.addAttribute("listeFilieres", filiereDAO.findAll());
		model.addAttribute("listeExamens", examenDAO.findAll());
		return "conduite/conduite";
	}

	@GetMapping("/change")
	public String change(Model model, Integer idinsc, Integer idcl, Integer idexa) {

		Inscription inscription = inscriptionDAO.findOne(idinsc);
		model.addAttribute("insc", inscription);
		model.addAttribute("idexa", idexa);
		return "conduite/changeConduite";
	}

	@GetMapping("/findList")
	public String findOne(Model model, Integer ida, Integer idant, Integer idniv, Integer idfil, Integer idexa) {

		if (ida == 0 || idant == 0 || idniv == 0 || idfil == 0 || idexa == 0) {
			model.addAttribute("erreur", "Tous les champs doivent être sélectionnés");
			return "/error/error";
		}

		Antenne ant = antenneDAO.findOne(idant);
		Annee an = anneeDAO.findOne(ida);
		Niveau niv = niveauDAO.findOne(idniv);
		Filiere fil = filiereDAO.findOne(idfil);
		Examen exa = examenDAO.findOne(idexa);
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
		model.addAttribute("listeEtudiants", listeInscrires);
		return "conduite/listeConduite";
	}

	@GetMapping("/changeConduite")
	public String changeCoduite(Model model, Integer idinsc, Integer idexa, Integer idcond) {

		Inscription inscription = inscriptionDAO.findOne(idinsc);
		String listet[];
		String listt[];

		if (!inscription.getConduite().equals("bonne")) {
			listet = inscription.getConduite().split("-");
			String conduite = null;
			if (listet.length > 1) {
				for (int i = 0; i < listet.length; i++) {
					listt = listet[i].split(";");
					if (Integer.parseInt(listt[0]) == idexa) {
						listt[1] = idcond + "";
						if (conduite == null)
							conduite = listt[0] + ";" + listt[1];
						else
							conduite += "-" + listt[0] + ";" + listt[1];

					} else {
						if (conduite == null)
							conduite = listt[0] + ";" + listt[1];
						else
							conduite += "-" + listt[0] + ";" + listt[1];
					}

				}

			} else {
				
				conduite = idexa + ";" + idcond;
			}
			inscription.setConduite(conduite);
		} else {
			inscription.setConduite(idexa + ";" + idcond);
		}

		inscriptionDAO.save(inscription);
		return "redirect:findList?ida=" + inscription.getAnnee().getId() + "&idant="
				+ inscription.getClasse().getAntenne().getId() + "&idniv=" + inscription.getClasse().getNiveau().getId()
				+ "&idfil=" + inscription.getClasse().getFiliere().getId() + "&idexa=" + idexa;
	}

}
