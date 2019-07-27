package com.boobacool.notemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilController {
	
	
	@GetMapping("/")
	public String Accueil() {
		return "index";
	}

}
