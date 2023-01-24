package ch.hearc.jee2022.myvines.cellar.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.hearc.jee2022.myvines.cellar.model.Vine;
import ch.hearc.jee2022.myvines.cellar.service.CellarService;

@Controller
public class VineController {
	@Autowired
	CellarService cellarService;

	private static final int ITEM_PER_PAGE = 10;

	@GetMapping(value = { "/", "home" })
	public String showHomePage(Model model, @RequestParam("page") Optional<Integer> page) {
		int currentPage = page.orElse(1);

		currentPage = currentPage < 1 ? 1 : currentPage;

		Page<Vine> vines = cellarService.getAllVinesFromCellar(PageRequest.of(currentPage - 1, ITEM_PER_PAGE));

		model.addAttribute("vines", vines.getContent());
		model.addAttribute("pages", vines);
		model.addAttribute("isPublic", Boolean.TRUE);
		return "home";
	}
}
