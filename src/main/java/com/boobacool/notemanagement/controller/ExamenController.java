package com.boobacool.notemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boobacool.notemanagement.dao.ExamenDAO;
import com.boobacool.notemanagement.models.Examen;

@Controller
@RequestMapping("/examen")
public class ExamenController {
	
	@Autowired
	private ExamenDAO examenDAO;
	
	@GetMapping("/examens")
	public String accueilexamen(Model model, @RequestParam(name="page",defaultValue = "0") Integer page, @RequestParam(name="size",defaultValue = "10") Integer size, @RequestParam(name="motcle", defaultValue="")String mc) {	
		Examen examen = new Examen();
		Page<Examen> listeExamen = examenDAO.listeExamens("%"+mc+"%",PageRequest.of(page, size));	
		int pagescount = listeExamen.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listeExamens", listeExamen);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        model.addAttribute("examen", examen);
        model.addAttribute("mc", mc);
		return "examen/examens";	
	}
	
	@PostMapping("/save")
	public String save(Examen examen) {
		examen.setLib(examen.getLib().toUpperCase());
		Examen ep = null;
		ep = examenDAO.findByLib(examen.getLib());
		if(ep == null)
		examenDAO.save(examen);
		
		return "redirect:/examen/examens";
	}
	
	@GetMapping("/delete")
	public String delete(Examen examen) {
		examenDAO.delete(examen);
		return "redirect:/examen/examens";
	}
	
	@GetMapping("/findOne")
	@ResponseBody
	public Examen findOne(Integer id) {
		return examenDAO.findOne(id);
	}

}
