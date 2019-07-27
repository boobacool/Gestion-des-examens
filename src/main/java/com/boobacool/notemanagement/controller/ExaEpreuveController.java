package com.boobacool.notemanagement.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boobacool.notemanagement.dao.AnneeDAO;
import com.boobacool.notemanagement.dao.AntenneDAO;
import com.boobacool.notemanagement.dao.ClasseDAO;
import com.boobacool.notemanagement.dao.EpreuveDAO;
import com.boobacool.notemanagement.dao.ExaEpreuveDAO;
import com.boobacool.notemanagement.dao.ExamenDAO;
import com.boobacool.notemanagement.dao.FiliereDAO;
import com.boobacool.notemanagement.dao.NiveauDAO;
import com.boobacool.notemanagement.dao.NotationDAO;
import com.boobacool.notemanagement.models.Annee;
import com.boobacool.notemanagement.models.Antenne;
import com.boobacool.notemanagement.models.Classe;
import com.boobacool.notemanagement.models.Epreuve;
import com.boobacool.notemanagement.models.EpreuveCoef;
import com.boobacool.notemanagement.models.ExaEpreuve;
import com.boobacool.notemanagement.models.ExaEpreuveListe;
import com.boobacool.notemanagement.models.Examen;
import com.boobacool.notemanagement.models.Filiere;
import com.boobacool.notemanagement.models.Niveau;
import com.boobacool.notemanagement.models.Notation;

@Controller
@RequestMapping("/exaepreuve")
public class ExaEpreuveController {

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
	private NotationDAO notationDAO;

	@Autowired
	private EpreuveDAO epreuveDAO;

	@GetMapping("/formulaire")
	public String formulaire(Model model) {

		model.addAttribute("listeAnnees", anneeDAO.findAll());
		model.addAttribute("listeAntennes", antenneDAO.findAll());
		model.addAttribute("listeNiveaux", niveauDAO.findAll());
		model.addAttribute("listeFilieres", filiereDAO.findAll());
		model.addAttribute("listeExamens", examenDAO.findAll());
		Integer nbres = 0;
		model.addAttribute("nombre", nbres);

		return "exaepreuve/exaepreuve";
	}
	
	@GetMapping("/supprimerMatiere")
	public String supprimerMatiere(Model model) {

		model.addAttribute("listeAnnees", anneeDAO.findAll());
		model.addAttribute("listeAntennes", antenneDAO.findAll());
		model.addAttribute("listeNiveaux", niveauDAO.findAll());
		model.addAttribute("listeFilieres", filiereDAO.findAll());
		model.addAttribute("listeExamens", examenDAO.findAll());
		return "exaepreuve/supprimerexa";
	}

	@GetMapping("/findList")
	public String findOne(Model model, Integer nombre, Integer ida, Integer idant, Integer idniv, Integer idfil,
			Integer idexa) {

		if (nombre == null || ida == 0 || idant == 0 || idniv == 0 || idfil == 0 || idexa == 0) {
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

		List<Epreuve> listeEpreuve = epreuveDAO.findAll();
		model.addAttribute("epreuves", listeEpreuve);
		model.addAttribute("ant", ant);
		model.addAttribute("an", an);
		model.addAttribute("cl", classe);
		model.addAttribute("exa", exa);

		ExaEpreuve exp = new ExaEpreuve();
		exp.setClasse(classe);
		exp.setAnnee(an);
		exp.setExamen(exa);
		ExaEpreuveListe epliste = new ExaEpreuveListe();
		epliste.setExaEpreuve(exp);
		for (int i = 0; i < nombre; i++) {
			Epreuve ep = new Epreuve();
			EpreuveCoef ec = new EpreuveCoef();
			ec.setEpreuve(ep);
			ec.setCoef(1);
			epliste.getListeEP().add(ec);
		}
		model.addAttribute("listep", epliste);
		return "exaepreuve/exaepreuves";
	}

	@PostMapping("/save")
	public String save(ExaEpreuveListe exaEpreuveListe, Integer idant, Integer idan, Integer idcl, Integer idexa) {

		Annee an = anneeDAO.findOne(idan);
		Classe cl = classeDAO.findOne(idcl);
		Examen exa = examenDAO.findOne(idexa);

		for (int i = 0; i < exaEpreuveListe.getListeEP().size(); i++) {
			ExaEpreuve exaEpreuve = new ExaEpreuve();
			exaEpreuve.setAnnee(an);
			exaEpreuve.setClasse(cl);
			exaEpreuve.setExamen(exa);
			exaEpreuve.setCoef(exaEpreuveListe.getListeEP().get(i).getCoef());
			Epreuve epreuve = epreuveDAO.findOne(exaEpreuveListe.getListeEP().get(i).getEpreuve().getId());
			if (exaEpreuveListe.getListeEP().get(i).getEpreuve().getId() != 0) {

				exaEpreuve.setEpreuve(epreuveDAO.findOne(exaEpreuveListe.getListeEP().get(i).getEpreuve().getId()));
				ExaEpreuve ep = null;
				ep = exaEpreuveDAO.findByAnneeAndClasseAndEpreuveAndExamen(an, cl, epreuve, exa);
				if (ep == null) {
					exaEpreuveDAO.save(exaEpreuve);
				}
				
			}
		}
		return "redirect:formulaire";
	}

	
	
	@RequestMapping(value="/listeMatieres/",method=RequestMethod.GET)
	@ResponseBody
	public List<ExaEpreuve> listeMatieres(@PathParam("ida")Integer ida,@PathParam("idant")Integer idant,@PathParam("idfil")Integer idfil,@PathParam("idniv")Integer idniv,
			@PathParam("idexa")Integer idexa) {
		
		Annee annee = anneeDAO.findOne(ida);
		Filiere filiere = filiereDAO.findOne(idfil);
		Niveau niveau = niveauDAO.findOne(idniv);
		Antenne antenne = antenneDAO.findOne(idant);
		Examen examen = examenDAO.findOne(idexa);
		Classe classe = classeDAO.findByAntenneAndFiliereAndNiveau(antenne, filiere, niveau);
		List<ExaEpreuve> listeS = exaEpreuveDAO.findByAnneeAndClasseAndAndExamen(annee, classe, examen);
		
		return listeS;
	}
	
	@GetMapping("/listeMatiere")
	public String supprimer(Model model,Integer ida, Integer idant, Integer idniv, Integer idfil,
			Integer idexa) {

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

		List<ExaEpreuve> listeExaepreuve = exaEpreuveDAO.findByAnneeAndClasseAndAndExamen(an, classe, exa);
		
		model.addAttribute("listeExaepreuves", listeExaepreuve);
		model.addAttribute("ant", ant);
		model.addAttribute("an", an);
		model.addAttribute("cl", classe);
		model.addAttribute("exa", exa);
		return "exaepreuve/listeExaepreuve";
	}
	
	
	@GetMapping("/supprimerListe")
	public String supprimerListe(Model model,Integer idep) {
        ExaEpreuve exaEpreuve = exaEpreuveDAO.findOne(idep);
		List<Notation> listeNotation = notationDAO.findByExaEpreuve(exaEpreuve);
		if(listeNotation.size() != 0) {
			for (int i = 0; i < listeNotation.size(); i++) {
				notationDAO.delete(listeNotation.get(i));
			}
		}
		exaEpreuveDAO.delete(exaEpreuve);
		return "redirect:/exaepreuve/supprimerMatiere";
	}

	
	
}
