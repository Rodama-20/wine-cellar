package ch.hearc.jee2022.myvines.cellar.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.hearc.jee2022.myvines.cellar.model.User;
import ch.hearc.jee2022.myvines.cellar.model.Vine;
import ch.hearc.jee2022.myvines.cellar.service.CellarService;

@Controller
public class VineController {
	@Autowired
	CellarService cellarService;

	private static final int ITEM_PER_PAGE = 5;

	@GetMapping(value = { "/", "home" })
	public String showHomePage(Model model, @RequestParam("page") Optional<Integer> page, Authentication auth) {
		if(auth != null) {
			User user = (User) auth.getPrincipal();
			model.addAttribute("userId", user.getId());
			model.addAttribute("isAdmin", user.isAdmin());
		} else {
			model.addAttribute("isAdmin", Boolean.FALSE);
		}


		int currentPage = page.orElse(1);

		currentPage = currentPage < 1 ? 1 : currentPage;

		Page<Vine> vines = cellarService.getAllVinesFromCellar(PageRequest.of(currentPage - 1, ITEM_PER_PAGE));
		
		int totalPages = vines.getTotalPages();
		if(totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
			model.addAttribute("pageMumbers", pageNumbers);
		}

		
		model.addAttribute("vinePage", vines);
		model.addAttribute("isPublic", Boolean.TRUE);
		
		
		return "home";
	}
	
	
}
