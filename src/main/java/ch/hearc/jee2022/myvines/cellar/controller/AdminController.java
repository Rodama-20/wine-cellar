package ch.hearc.jee2022.myvines.cellar.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.hearc.jee2022.myvines.cellar.model.Vine;
import ch.hearc.jee2022.myvines.cellar.service.CellarService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	CellarService cellarService;

	private static final int ITEM_PER_PAGE = 5;

	@PostMapping(value = "/save-vine")
	public String saveVine(@ModelAttribute Vine vine, BindingResult errors, Model model, @RequestParam String type) {

		if (type.equals("new")) {
			cellarService.addVineToCellar(vine);
		} else {
			cellarService.deleteVine(vine.getId().longValue());
			cellarService.addVineToCellar(vine);
		}

		return "redirect:/admin/"; //
	}

	@PostMapping(value = "/delete-vine")
	private String deleteVine(@RequestParam Integer id) {
		cellarService.deleteVine(id.longValue());
		return "redirect:/admin/";
	}

	@GetMapping(value = "/new-vine")
	public String showNewVineForm(Model model) {
		model.addAttribute("vine", new Vine());
		model.addAttribute("showList", Boolean.FALSE);
		model.addAttribute("showNew", Boolean.TRUE);

		model.addAttribute("isNew", Boolean.TRUE);
		model.addAttribute("isEdit", Boolean.FALSE);
		return "admin/home";
	}

	@PostMapping(value = "/edit-vine")
	public String showEditVineForm(Model model, @RequestParam Integer id) {

		Vine vineToEdit = cellarService.getVineById(id.longValue());

		model.addAttribute("vine", vineToEdit);
		model.addAttribute("showList", Boolean.FALSE);
		model.addAttribute("showNew", Boolean.TRUE);

		model.addAttribute("isEdit", Boolean.TRUE);
		model.addAttribute("isNew", Boolean.FALSE);

		return "admin/home";
	}

	@GetMapping(value = { "/" })
	public String showHomePage(Model model, @RequestParam("page") Optional<Integer> page) {
		int currentPage = page.orElse(1);

		currentPage = currentPage < 1 ? 1 : currentPage;

		Page<Vine> vines = cellarService.getAllVinesFromCellar(PageRequest.of(currentPage - 1, ITEM_PER_PAGE));
		int totalPages = vines.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
			model.addAttribute("pageMumbers", pageNumbers);
		}

		model.addAttribute("showList", Boolean.TRUE);
		model.addAttribute("showNew", Boolean.FALSE);
		model.addAttribute("isAdmin", Boolean.TRUE);
		model.addAttribute("vinePage", vines);
		model.addAttribute("isEditing", Boolean.TRUE);
		return "admin/home";
	}

}
