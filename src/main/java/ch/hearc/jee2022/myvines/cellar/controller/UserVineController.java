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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.hearc.jee2022.myvines.cellar.model.User;
import ch.hearc.jee2022.myvines.cellar.model.UserVine;
import ch.hearc.jee2022.myvines.cellar.model.Vine;
import ch.hearc.jee2022.myvines.cellar.service.CellarService;
import ch.hearc.jee2022.myvines.cellar.service.UserVineService;

@Controller
public class UserVineController {

	@Autowired
	UserVineService userVineService;
	@Autowired
	CellarService cellarService;
	
	private static final int ITEM_PER_PAGE = 5;

	@GetMapping(value = "my-cellar")
	public String myCellar(Model model, @RequestParam("page") Optional<Integer> page, Authentication auth) {
		User user = (User) auth.getPrincipal();
		List<UserVine> list = userVineService.getAllVineFormUser(user);
		
		Double value = UserVine.cellarValue(list).orElse(0.0);
		
		int currentPage = page.orElse(1);
		
		currentPage = currentPage < 1 ? 1 : currentPage;
		
		Page<UserVine> uvPage = userVineService.getAllVineFormUserPageable(user, PageRequest.of(currentPage, ITEM_PER_PAGE));
		
		int totalPages = uvPage.getTotalPages();
		if(totalPages > 0)
		{
			List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().toList();
			model.addAttribute("pageNumbers", pages);
		}
		model.addAttribute("uvPage", uvPage);
		
		return "cellar";
		
	}

	@PostMapping(value = "add-to-cellar")
	public String addToCellar(Model model, @RequestParam Long vineId, Authentication auth) {

		User user = (User) auth.getPrincipal();
		Vine vine = cellarService.getVineById(vineId);

		if (userVineService.getUV(user, vine).isEmpty()) {

			UserVine uv = new UserVine();

			uv.setUser(user);
			uv.setVine(vine);
			uv.setAmount(0);

		}

		return "redirect:/my-cellar/";

	}

}
