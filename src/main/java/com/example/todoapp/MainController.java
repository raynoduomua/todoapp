package com.example.todoapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	/**
	 * ログイン画面表示
	 * http://localhost:8080/loginForm
	 * admin admin@admin.jp password
	 * user  test@test.jp   password
	 * http://localhost:8080/h2-console
	 * 
	 * @return ログイン画面
	 */
	@GetMapping("/loginForm")
	public String viewLogin() {
		return "loginForm";
	}

}
