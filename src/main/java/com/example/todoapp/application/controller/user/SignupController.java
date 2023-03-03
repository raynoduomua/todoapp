package com.example.todoapp.application.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todoapp.application.common.utils.Type;
import com.example.todoapp.application.form_validation.GroupOrder;
import com.example.todoapp.application.form_validation.UserForm;
import com.example.todoapp.domain.service.user.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignupController {

	private final UserService userService;

	@GetMapping("/register")
	public String viewSignup(Model model, @ModelAttribute("userForm") UserForm userForm) {

		// 性別を取得
		model.addAttribute("genderMap", Type.getGenderMap());

		return "user/usersignup";
	}

	@PostMapping("/register")
	public String saveUser(Model model, @Validated(GroupOrder.class) @ModelAttribute("userForm") UserForm userForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return viewSignup(model, userForm);
		}

		this.userService.saveUser(userForm);

		return "redirect:/loginForm?register";
	}
}
