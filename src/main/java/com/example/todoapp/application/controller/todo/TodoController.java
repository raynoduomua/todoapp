package com.example.todoapp.application.controller.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todoapp.application.common.security.LoginUser;
import com.example.todoapp.application.common.utils.Selection;
import com.example.todoapp.application.form_validation.GroupOrder;
import com.example.todoapp.application.form_validation.TodoForm;
import com.example.todoapp.domain.service.todo.TodoService;
import com.example.todoapp.infrastructure.entity.todo.Todo;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;

	/**
	 * Home画面表示
	 * @param model     Modelクラス
	 * @param loginUser ログイン中のユーザー情報
	 * @param todoForm  Formクラス
	 * @return          home画面
	 */
	@GetMapping("/home")
	public String viewHome(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("todoForm") TodoForm todoForm) {

		List<Todo> todos = this.todoService.findByUseridAndDone(null, loginUser);
		model.addAttribute("todos", todos);

		model.addAttribute("selectMap", Selection.getSelectMap());

		return "home";
	}

	/**
	 * Todo新規登録処理
	 * @param model         Modelクラス
	 * @param loginUser     ログイン中のユーザー情報
	 * @param todoForm      Formクラス
	 * @param bindingResult バリデーションチェック
	 * @return              true  home画面(redirect)
	 *                      false home画面
	 */
	@PostMapping("/save")
	public String saveTodo(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@Validated(GroupOrder.class) @ModelAttribute("todoForm") TodoForm todoForm,
			BindingResult bindingResult) {

		// 入力チェック結果
		if (bindingResult.hasErrors()) {
			// NG:home画面に戻ります
			return viewHome(model, loginUser, todoForm);
		}

		this.todoService.saveTodo(todoForm, loginUser);

		return "redirect:/todo/home?save";
	}

	/**
	 * 選択肢
	 * @param id        選択肢Value
	 * @param model     Modelクラス
	 * @param loginUser ログイン中のユーザー情報
	 * @param todoForm  Formクラス
	 * @return          home画面
	 */
	@GetMapping("/select/{id}")
	public String selectTodo(@PathVariable("id") Integer id, Model model,
			@AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("todoForm") TodoForm todoForm) {

		List<Todo> todos = this.todoService.findByUseridAndDone(id, loginUser);
		model.addAttribute("todos", todos);

		model.addAttribute("selectMap", Selection.getSelectMap());

		// model.addAttribute("user", loginUser.getUser());

		return "home";
	}

	/**
	 * Todo完了処理
	 * @param id        todoitems.id
	 * @param loginUser ログイン中のユーザー情報
	 * @return          home画面(redirect)
	 */
	@PostMapping("/done")
	public String doneTodo(Integer id, @AuthenticationPrincipal LoginUser loginUser) {

		this.todoService.doneTodo(id, loginUser);

		return "redirect:/todo/home?done";
	}

	/**
	 * Todo更新画面表示
	 * @param id       Todo.id
	 * @param model    Modelクラス
	 * @param todoForm Formクラス
	 * @return         Todo更新画面
	 */
	@PostMapping("/edit")
	public String viewEdit(Integer id, Model model, @ModelAttribute("todoForm") TodoForm todoForm) {

		Optional<Todo> todo = this.todoService.findById(id);
		todoForm.setId((int) todo.get().getId().longValue());
		todoForm.setContents(todo.get().getContents());

		return "todo/edit";
	}

	/**
	 * Todo更新
	 * @param model         Modelクラス
	 * @param todoForm      Formクラス
	 * @param bindingResult バリデーションチェック
	 * @return              true  home画面(redirect)
	 *                      false Todo更新画面
	 */
	@PostMapping("/update")
	public String updateTodo(Model model,
			@Validated(GroupOrder.class) @ModelAttribute("todoForm") TodoForm todoForm,
			BindingResult bindingResult) {

		// 入力チェック結果
		if (bindingResult.hasErrors()) {
			// NG:Todo更新画面に戻ります
			return viewEdit(todoForm.getId(), model, todoForm);
		}

		this.todoService.updateTodo(todoForm);

		return "redirect:/todo/home?update";
	}

	/**
	 * Todo削除（論理削除）
	 * @param id Todo.id
	 * @return   home画面(redirect)
	 */
	@PostMapping("/delete")
	public String deleteTodo(Integer id) {

		this.todoService.deleteTodo(id);

		return "redirect:/todo/home?delete";
	}

}
