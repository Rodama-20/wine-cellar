package ch.hearc.jee2022.myvines.cellar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ch.hearc.jee2022.myvines.cellar.service.CellarService;

@Controller
public class VineController {
	@Autowired
	CellarService cellarService;

	@GetMapping(value = { "/", "home" })
	public String showHomePage(Model model) {
		model.addAttribute("vines", cellarService.getAllVinesFromCellar());
		model.addAttribute("isPublic", Boolean.TRUE);
		return "home";
	}
}
