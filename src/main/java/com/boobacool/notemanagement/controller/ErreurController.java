package com.boobacool.notemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/error")
public class ErreurController {
	
	
@GetMapping("/erreurpage")
public String page(Model model,String erreur) {	
	model.addAttribute("erreur", erreur);
	return "error";
}

}
