package ch.hearc.jee2022.myvines.cellar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	/**
	 * Show the cellar for the current user
	 * 
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping(value = "my-cellar")
	public String myCellar(Model model, Authentication auth) {
		User user = (User) auth.getPrincipal();
		List<UserVine> list = userVineService.getAllVineFormUser(user);

		Double value = UserVine.cellarValue(list).orElse(0.0);

		model.addAttribute("uvs", list);
		model.addAttribute("myCellar", Boolean.TRUE);
		model.addAttribute("value", value);
		model.addAttribute("isAdmin", user.isAdmin());

		return "cellar";

	}

	/**
	 * Add a vine to the cellar of the current user with a zero quantity.
	 * 
	 * The quantity has to be adjust later
	 * 
	 * @param model
	 * @param vineId
	 * @param auth
	 * @return
	 */
	@PostMapping(value = "add-to-cellar")
	public String addToCellar(Model model, @RequestParam Long vineId, Authentication auth) {

		User user = (User) auth.getPrincipal();
		Vine vine = cellarService.getVineById(vineId);

		if (userVineService.getUV(user, vine).isEmpty()) {

			UserVine uv = new UserVine();

			uv.setUser(user);
			uv.setVine(vine);
			uv.setAmount(0);

			userVineService.addVineToUser(uv);

		}

		return "redirect:/my-cellar/";

	}

	/**
	 * Update the quantity of vine in the cellar
	 * 
	 * @param model
	 * @param vineId
	 * @param amount
	 * @param auth
	 * @return
	 */
	@PostMapping(value = "edit")
	public String edit(Model model, @RequestParam Long vineId, @RequestParam Integer amount, Authentication auth) {
		User user = (User) auth.getPrincipal();

		userVineService.editAmount(user, vineId, amount);
		return "redirect:/my-cellar/";
	}

	/**
	 * Remove a vine form the current user
	 * 
	 * @param model
	 * @param vineId
	 * @param auth
	 * @return
	 */
	@PostMapping(value = "remove-from-cellar")
	public String remove(Model model, @RequestParam Long vineId, Authentication auth) {
		User user = (User) auth.getPrincipal();

		UserVine uv = userVineService.getUV(user, cellarService.getVineById(vineId)).get();
		userVineService.removeVineFromUser(uv);
		return "redirect:/my-cellar/";
	}

}
