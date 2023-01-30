package ch.hearc.jee2022.myvines.cellar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import ch.hearc.jee2022.myvines.cellar.model.UserDto;
import ch.hearc.jee2022.myvines.cellar.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * Show the registration form
	 * 
	 * @param request
	 * @param model
	 * @return the registration template
	 */
	@GetMapping("/register")
	public String showRegistrationForm(WebRequest request, Model model) {

		model.addAttribute("userDto", new UserDto());
		return "registration";
	}

	/**
	 * Proceed the registration form and register the user. Redirect to the login on
	 * success or on the form when there are problem
	 * 
	 * @param userDto
	 * @param errors
	 * @param model
	 * @return
	 */
	@PostMapping("/register")
	public String registerUserAccount(@ModelAttribute UserDto userDto, BindingResult errors, Model model) {

		if (userDto.getPassword().equals(userDto.getMatchingPassword())) {

			try {
				userService.register(userDto);
			} catch (Exception e) {
				return "redirect:/register";
			}
			return "redirect:/login";
		} else {
			return "redirect:/register";
		}

	}
}