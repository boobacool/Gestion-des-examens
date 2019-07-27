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

import com.boobacool.notemanagement.dao.EpreuveDAO;
import com.boobacool.notemanagement.models.Epreuve;

@Controller
@RequestMapping("/epreuve")
public class EpreuveController {
	
	
	@Autowired
	private EpreuveDAO epreuveDAO;
	
	@GetMapping("/epreuves")
	public String accueilepreuve(Model model, @RequestParam(name="page",defaultValue = "0") Integer page, @RequestParam(name="size",defaultValue = "10") Integer size, @RequestParam(name="motcle", defaultValue="")String mc) {	
		Epreuve epreuve = new Epreuve();
		Page<Epreuve> listeEpreuves = epreuveDAO.listeEpreuves("%"+mc+"%",PageRequest.of(page, size));	
		int pagescount = listeEpreuves.getTotalPages();
        int[] pages= new int[pagescount];
        for(int i=0;i<pagescount;i++) pages[i]=i;
        model.addAttribute("listeEpreuves", listeEpreuves);
        model.addAttribute("pages", pages);
        model.addAttribute("pageEnCours", page);
        model.addAttribute("epreuve", epreuve);
        model.addAttribute("mc", mc);
		return "epreuve/epreuves";	
	}
	
	@PostMapping("/save")
	public String save(Epreuve epreuve) {
		epreuve.setLib(epreuve.getLib().toUpperCase());
		Epreuve ep = null;
		ep = epreuveDAO.findByLib(epreuve.getLib());
		if(ep == null)
		epreuveDAO.save(epreuve);
		
		return "redirect:/epreuve/epreuves";
	}
	
	@GetMapping("/delete")
	public String delete(Epreuve epreuve) {
		epreuveDAO.delete(epreuve);
		return "redirect:/epreuve/epreuves";
	}
	
	@GetMapping("/findOne")
	@ResponseBody
	public Epreuve findOne(Integer id) {
		return epreuveDAO.findOne(id);
	}

}
