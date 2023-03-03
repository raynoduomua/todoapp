package com.example.todoapp.application.controller.user;

import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todoapp.application.common.security.LoginUser;
import com.example.todoapp.application.common.utils.Selection;
import com.example.todoapp.domain.service.user.UserService;
import com.example.todoapp.infrastructure.entity.common.Account;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	/**
	 * ログイン情報画面表示
	 * 
	 * @param model     Modelクラス
	 * @param loginUser ログイン中のユーザー情報
	 * @return          ログイン情報画面
	 */
	@GetMapping("/loginInfo")
	public String viewLoginInfo(Model model, @AuthenticationPrincipal LoginUser loginUser) {

		Optional<Account> userOptional = this.userService.findById(loginUser);
		userOptional.ifPresent(user -> {
			user.setPassword(null);
			model.addAttribute("userInfo", user);
		});

		model.addAttribute("changeInfoMap", Selection.changeInfoMap());

		return "common/loginInfo";
	}

	/**
	 * 退会アカウント情報画面表示
	 * @param model     Modelクラス
	 * @param loginUser ログイン中のユーザ情報
	 * @return          退会アカウント情報画面
	 */
	@GetMapping("/deleteInfo")
	public String viewDeleteInfo(Model model, @AuthenticationPrincipal LoginUser loginUser) {

		Optional<Account> userOptional = this.userService.findById(loginUser);
		userOptional.ifPresent(user -> {
			user.setPassword(null);
			model.addAttribute("userInfo", user);
		});

		return "user/deleteInfo";
	}

	/**
	 * 退会処理
	 * @param loginUser ログイン中のユーザ情報
	 * @return          ログイン画面
	 */
	@PostMapping("/delete")
	public String accountDelete(@AuthenticationPrincipal LoginUser loginUser) {

		this.userService.accountDelete(loginUser);

		return "redirect:/loginForm?delete";
	}

}
