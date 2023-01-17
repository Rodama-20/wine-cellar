package ch.hearc.jee2022.myvines.cellar.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.hearc.beershopfull.catalog.model.Beer;
import ch.hearc.beershopfull.catalog.service.CatalogService;
import ch.hearc.jee2022.myvines.cellar.model.Vine;
import ch.hearc.jee2022.myvines.cellar.service.CellarService;

import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controlleur lié au catalogue de bières
 * Une classe annoté @Controller, avec la librairie thymeleaf, permettra de retourner les noms des templates 
 * à service à l'utilisateur
 * @author Seb
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	CellarService cellarService;
	
	
	
	@PostMapping(value = "/save-vine")
	public String saveBeer(@ModelAttribute Vine vine, BindingResult errors, Model model,@RequestParam String type) {
		
		if(type.equals("new")) {
			cellarService.addVineToCellar(vine);
		}else {
			cellarService.deleteVine(vine.getId());
			cellarService.addVineToCellar(vine);
		}
		
       return "redirect:/admin/"; //
    }
	
	@PostMapping(value = "/delete-vine")
	private String deleteStudent(@RequestParam Integer id){
	    cellarService.deleteVine(new Long(id));
	    return "redirect:/admin/";
	}
	
	
	@GetMapping(value = "/new-vine")
	public String showNewBeerForm(Model model) {
	    model.addAttribute("beer", new Vine());   
	    model.addAttribute("showList",Boolean.FALSE);
	    model.addAttribute("showNew",Boolean.TRUE);
	    
	    model.addAttribute("isNew",Boolean.TRUE);
	    model.addAttribute("isEdit",Boolean.FALSE);
		return "admin/accueil";
	}
	
	
	@PostMapping(value = "/edit-vine")
	public String showNEditBeerForm(Model model,@RequestParam Integer id) {
	    
		Vine vineToEdit = cellarService.getVineById(id.longValue());
		
		model.addAttribute("beer", vineToEdit);   
	    model.addAttribute("showList",Boolean.FALSE);
	    model.addAttribute("showNew",Boolean.TRUE);
	    
	    model.addAttribute("isEdit",Boolean.TRUE);
	    model.addAttribute("isNew",Boolean.FALSE);
	    
	    
	    
		return "admin/accueil";
	}
	
	
	@GetMapping(value = {"/"})
	public String showAccueilPage(Model model) {
	    model.addAttribute("showList",Boolean.TRUE);
	    model.addAttribute("showNew",Boolean.FALSE);
	    model.addAttribute("isAdmin",Boolean.TRUE);
	    model.addAttribute("beers", cellarService.getAllVinesFromCellar());  
	    return "admin/accueil";
	}
	
	
}
