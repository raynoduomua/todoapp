package com.example.todoapp.application.controller.user;

import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todoapp.application.common.security.LoginUser;
import com.example.todoapp.application.common.utils.Type;
import com.example.todoapp.application.form_validation.GroupOrder;
import com.example.todoapp.application.form_validation.UserEmailForm;
import com.example.todoapp.application.form_validation.UserGenderForm;
import com.example.todoapp.application.form_validation.UserNameForm;
import com.example.todoapp.application.form_validation.UserPasswordForm;
import com.example.todoapp.domain.service.user.UserService;
import com.example.todoapp.infrastructure.entity.common.Account;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserDetailController {

	private final UserService userService;

	/**
	 * ユーザー名更新画面表示
	 * @param id　　　　　　　　　ユーザ情報変更MapValue
	 * @param model        Modelクラス
	 * @param loginUser    ログイン中のユーザー情報
	 * @param userNameForm Formクラス
	 * @return             ユーザー名情報変更画面
	 */
	@PostMapping("/username")
	public String viewUserName(Integer id, Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("userNameForm") UserNameForm userNameForm) {

		Optional<Account> userOptional = this.userService.findById(loginUser);
		userOptional.ifPresent(user -> {
			userNameForm.setUsername(user.getUsername());
		});

		return "common/userNameDetail";
	}

	/**
	 * ユーザー名更新処理
	 * @param id            ユーザ情報変更MapValue
	 * @param model         Modelクラス
	 * @param loginUser     ログイン中のユーザ情報
	 * @param userNameForm  formクラス
	 * @param bindingResult バリデーションチェック
	 * @return              true  ログイン情報画面（リダイレクト）
	 *                      false ユーザー名情報変更画面
	 */
	@PostMapping("/updateUserName")
	public String updateUserName(Integer id, Model model, @AuthenticationPrincipal LoginUser loginUser,
			@Validated(GroupOrder.class) @ModelAttribute("userNameForm") UserNameForm userNameForm,
			BindingResult bindingResult) {

		// 入力チェック結果
		if (bindingResult.hasErrors()) {
			return viewUserName(id, model, loginUser, userNameForm);
		}

		this.userService.updateUserName(loginUser, userNameForm);

		return "redirect:/loginInfo?update";
	}

	/**
	 * メールアドレス更新画面表示
	 * @param id            ユーザ情報変更MapValue
	 * @param model         Modelクラス
	 * @param loginUser     ログイン中のユーザ情報
	 * @param userEmailForm formクラス
	 * @return              メールアドレス情報変更画面
	 */
	@PostMapping("/email")
	public String viewEmail(Integer id, Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("userEmailForm") UserEmailForm userEmailForm) {

		Optional<Account> userOptional = this.userService.findById(loginUser);
		userOptional.ifPresent(user -> {
			userEmailForm.setEmail(user.getEmail());
		});

		return "common/emailDetail";
	}

	/**
	 * メールアドレス更新処理
	 * @param id            ユーザ情報変更MapValue
	 * @param model         Modelクラス
	 * @param loginUser     ログイン中のユーザ情報
	 * @param userEmailForm formクラス
	 * @param bindingResult バリデーションチェック
	 * @return              true  ログイン情報画面（リダイレクト）
	 *                      false メールアドレス情報変更画面
	 */
	@PostMapping("/updateEmail")
	public String updateEmail(Integer id, Model model, @AuthenticationPrincipal LoginUser loginUser,
			@Validated(GroupOrder.class) @ModelAttribute("userEmailForm") UserEmailForm userEmailForm,
			BindingResult bindingResult) {

		// 入力チェック結果
		if (bindingResult.hasErrors()) {
			return viewEmail(id, model, loginUser, userEmailForm);
		}

		this.userService.updateEmail(loginUser, userEmailForm);

		return "redirect:/loginInfo?update";
	}

	/**
	 * パスワード更新画面表示
	 * @param id               ユーザ情報変更MapValue
	 * @param model            Modelクラス
	 * @param loginUser        ログイン中のユーザ情報
	 * @param userPasswordForm Formクラス
	 * @return                 パスワード情報変更画面
	 */
	@PostMapping("/password")
	public String viewPassword(Integer id, Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("userPasswordForm") UserPasswordForm userPasswordForm) {

		Optional<Account> userOptional = this.userService.findById(loginUser);
		userOptional.ifPresent(user -> {
			userPasswordForm.setPassword(user.getPassword());
		});

		return "common/passwordDetail";
	}

	/**
	 * パスワード更新処理
	 * @param id               ユーザ情報変更MapValue
	 * @param model            Modelクラス
	 * @param loginUser        ログイン中のユーザ情報
	 * @param userPasswordForm Formクラス
	 * @param bindingResult    バリデーションチェック
	 * @return　　　　　　　　　　　　　　true  ログイン情報画面（リダイレクト）
	 *                         false パスワード情報変更画面
	 */
	@PostMapping("/updatePassword")
	public String updatePassword(Integer id, Model model, @AuthenticationPrincipal LoginUser loginUser,
			@Validated(GroupOrder.class) @ModelAttribute("userPasswordForm") UserPasswordForm userPasswordForm,
			BindingResult bindingResult) {

		// 入力チェック結果
		if (bindingResult.hasErrors()) {
			return viewPassword(id, model, loginUser, userPasswordForm);
		}

		this.userService.updatePassword(loginUser, userPasswordForm);

		return "redirect:/loginInfo?update";
	}

	/**
	 * 性別更新画面表示
	 * @param id             ユーザ情報変更MapValue
	 * @param model          Modelクラス
	 * @param loginUser      ログイン中のユーザ情報
	 * @param userGenderForm Formクラス
	 * @return               性別変更画面表示
	 */
	@PostMapping("/gender")
	public String viewGender(Integer id, Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("userGenderForm") UserGenderForm userGenderForm) {

		Optional<Account> userOptional = this.userService.findById(loginUser);
		userOptional.ifPresent(user -> {
			userGenderForm.setGender(user.getGender());
		});

		// 性別を取得
		model.addAttribute("genderMap", Type.getGenderMap());

		return "common/genderDetail";
	}

	/**
	 * 性別更新処理
	 * @param id             ユーザ情報変更MapValue
	 * @param model          Modelクラス
	 * @param loginUser      ログイン中のユーザ情報
	 * @param userGenderForm Formクラス
	 * @param bindingResult  バリデーションチェック
	 * @return               true  ログイン情報画面（リダイレクト）
	 *                       false 性別情報変更画面
	 */
	@PostMapping("/updateGender")
	public String updateGender(Integer id, Model model, @AuthenticationPrincipal LoginUser loginUser,
			@Validated(GroupOrder.class) @ModelAttribute("userGenderForm") UserGenderForm userGenderForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return viewGender(id, model, loginUser, userGenderForm);
		}

		this.userService.updateGender(loginUser, userGenderForm);

		return "redirect:/loginInfo?update";
	}

}
