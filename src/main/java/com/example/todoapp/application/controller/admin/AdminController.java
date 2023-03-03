package com.example.todoapp.application.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todoapp.application.common.utils.Type;
import com.example.todoapp.application.form_validation.AdminForm;
import com.example.todoapp.application.form_validation.GroupOrder;
import com.example.todoapp.domain.service.admin.AdminService;
import com.example.todoapp.domain.service.todo.TodoService;
import com.example.todoapp.infrastructure.entity.common.Account;
import com.example.todoapp.infrastructure.entity.todo.Todo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	private final AdminService adminService;
	private final TodoService todoService;

	/**
	 * アカウント一覧画面表示
	 * @param model Modelクラス
	 * @return      アカウント一覧画面
	 */
	@GetMapping("/list")
	public String viewList(Model model) {

		List<Account> accounts = this.adminService.findAccountAll();
		model.addAttribute("accounts", accounts);

		return "admin/list";
	}

	@GetMapping("/todolist")
	public String viewTodoList(Model model) {

		List<Todo> alltodo = this.todoService.searchTodoAll();
		model.addAttribute("alltodo", alltodo);

		return "admin/todolist";
	}

	/**
	 * 管理者登録画面表示
	 * @param model     Modelクラス
	 * @param adminForm Formクラス
	 * @return          管理者登録画面
	 */
	@GetMapping("/register")
	public String viewAdminSignup(Model model, @ModelAttribute("adminForm") AdminForm adminForm) {

		// 性別を取得
		model.addAttribute("genderMap", Type.getGenderMap());

		return "admin/adminsignup";
	}

	/**
	 * 管理者登録処理
	 * @param model         Modelクラス
	 * @param adminForm     Formクラス
	 * @param bindingResult バリデーションチェック
	 * @return              true  home画面(redirect)
	 *                      false 管理者登録画面
	 */
	@PostMapping("/register")
	public String saveAdmin(Model model, @Validated(GroupOrder.class) @ModelAttribute("adminForm") AdminForm adminForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return viewAdminSignup(model, adminForm);
		}

		this.adminService.saveAdmin(adminForm);

		return "redirect:/todo/home?adminsave";
	}

}
