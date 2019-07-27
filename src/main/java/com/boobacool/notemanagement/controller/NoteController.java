package com.boobacool.notemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.boobacool.notemanagement.models.InsNoteListes;
import com.boobacool.notemanagement.models.Inscription;
import com.boobacool.notemanagement.models.Niveau;
import com.boobacool.notemanagement.models.Notation;

@Controller
@RequestMapping("/note")
public class NoteController {

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
		Integer nbres = 0;
		model.addAttribute("nombre", nbres);

		return "note/note";
	}

	@GetMapping("/findList")
	public String findOne(Model model, Integer ida, Integer idant, Integer idniv, Integer idfil, Integer idexa,
			Integer idep) {

		if (ida == 0 || idant == 0 || idniv == 0 || idfil == 0 || idexa == 0 || idep == 0) {
			model.addAttribute("erreur", "Tous les champs doivent être sélectionnés");
			return "/error/error";
		}

		Antenne ant = antenneDAO.findOne(idant);
		Examen exa = examenDAO.findOne(idexa);
		Annee an = anneeDAO.findOne(ida);
		Niveau niv = niveauDAO.findOne(idniv);
		Filiere fil = filiereDAO.findOne(idfil);
		ExaEpreuve exaEpreuve = exaEpreuveDAO.findOne(idep);
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
		model.addAttribute("exaepreuve", exaEpreuve);

		List<Inscription> listeInscrires = inscriptionDAO.findByClasseAndAnnee(classe, an);
		InsNoteListes insNoteListes = new InsNoteListes();
		for (int i = 0; i < listeInscrires.size(); i++) {
			Notation notation = null;
			notation = notationDAO.findByInscriptionAndExaEpreuve(listeInscrires.get(i), exaEpreuve);
			// insNoteListes.getListeInsNotes().add(notation);
			if (notation == null) {
				Notation notation2 = new Notation();
				notation2.setNote(0.0);
				notation2.setExaEpreuve(exaEpreuve);
				notation2.setInscription(listeInscrires.get(i));
				insNoteListes.getListeInsNotes().add(notation2);
				// System.out.println(insNoteListes.getListeInsNotes().get(i).getInscription().getEtudiant().getNom());

			} else {
				insNoteListes.getListeInsNotes().add(notation);
			}

		}

		model.addAttribute("insNoteListes", insNoteListes);
		return "note/notes";
	}

	@PostMapping("/save")
	public String save(InsNoteListes insNoteListes, Integer idexaep, Integer idan, Integer idcl, Integer idexa) {

		Annee an = anneeDAO.findOne(idan);
		Classe cl = classeDAO.findOne(idcl);
		Examen exa = examenDAO.findOne(idexa);
		ExaEpreuve exaEpreuve = exaEpreuveDAO.findOne(idexaep);
		for (int i = 0; i < insNoteListes.getListeInsNotes().size(); i++) {

			insNoteListes.getListeInsNotes().get(i).setExaEpreuve(exaEpreuve);

			if (insNoteListes.getListeInsNotes().get(i).getNote() == null) {
				insNoteListes.getListeInsNotes().get(i).setNote(0.0);
				notationDAO.save(insNoteListes.getListeInsNotes().get(i));
				} else {
				notationDAO.save(insNoteListes.getListeInsNotes().get(i));
			}

		}
		return "redirect:formulaire";
	}

	/*
	 * @RequestMapping(value="/listeMatieres/",method=RequestMethod.GET)
	 * 
	 * @ResponseBody public List<ExaEpreuve> listeMatieres(@PathParam("ida")Integer
	 * ida,@PathParam("idant")Integer idant,@PathParam("idfil")Integer
	 * idfil,@PathParam("idniv")Integer idniv,
	 * 
	 * @PathParam("idexa")Integer idexa) {
	 * 
	 * Annee annee = anneeDAO.findOne(ida); Filiere filiere =
	 * filiereDAO.findOne(idfil); Niveau niveau = niveauDAO.findOne(idniv); Antenne
	 * antenne = antenneDAO.findOne(idant); Examen examen =
	 * examenDAO.findOne(idexa); Classe classe =
	 * classeDAO.findByAntenneAndFiliereAndNiveau(antenne, filiere, niveau);
	 * List<ExaEpreuve> listeS =
	 * exaEpreuveDAO.findByAnneeAndClasseAndAndExamen(annee, classe, examen);
	 * 
	 * return listeS; }
	 * 
	 */
}
