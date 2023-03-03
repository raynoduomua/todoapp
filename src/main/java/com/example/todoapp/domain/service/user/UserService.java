package com.example.todoapp.domain.service.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todoapp.application.common.security.LoginUser;
import com.example.todoapp.application.common.utils.LocalDateNow;
import com.example.todoapp.application.form_validation.UserEmailForm;
import com.example.todoapp.application.form_validation.UserForm;
import com.example.todoapp.application.form_validation.UserGenderForm;
import com.example.todoapp.application.form_validation.UserNameForm;
import com.example.todoapp.application.form_validation.UserPasswordForm;
import com.example.todoapp.infrastructure.entity.common.Account;
import com.example.todoapp.infrastructure.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	/**
	 * ユーザー登録
	 * @param userForm Formクラス
	 */
	public void saveUser(UserForm userForm) {

		Account user = new Account();
		user.setUsername(userForm.getUsername());
		user.setEmail(userForm.getEmail());
		user.setPassword(passwordEncoder.encode(userForm.getPassword()));
		user.setAge(userForm.getAge());
		user.setGender(userForm.getGender());
		user.setAdmin(false);
		//		user.setAuthority(Authority.USER);
		user.setCreatedAt(LocalDateNow.getLocalDateNow());
		user.setUpdatedAt(null);
		user.setDeletedAt(null);

		this.userRepository.save(user);
	}

	/**
	 * ユーザー1件取得
	 * @param loginUser ログイン中のユーザー情報
	 * @return
	 */
	public Optional<Account> findById(LoginUser loginUser) {
		return this.userRepository.findById(loginUser.getUser().getId());
	}

	/**
	 * ユーザー名更新処理
	 * @param loginUser    ログイン中のユーザ情報
	 * @param userNameForm Formクラス
	 */
	public void updateUserName(LoginUser loginUser, UserNameForm userNameForm) {

		Optional<Account> userOptional = this.findById(loginUser);
		userOptional.ifPresent(user -> {
			user.setUsername(userNameForm.getUsername());
			user.setUpdatedAt(LocalDateNow.getLocalDateNow());
			this.userRepository.save(user);
		});
	}

	/**
	 * ユーザーメールアドレス更新処理
	 * @param loginUser     ログイン中のユーザ情報
	 * @param userEmailForm Formクラス
	 */
	public void updateEmail(LoginUser loginUser, UserEmailForm userEmailForm) {

		Optional<Account> userOptional = this.findById(loginUser);
		userOptional.ifPresent(user -> {
			user.setEmail(userEmailForm.getEmail());
			user.setUpdatedAt(LocalDateNow.getLocalDateNow());
			this.userRepository.save(user);
		});
	}

	/**
	 * パスワード更新処理
	 * @param loginUser        ログイン中のユーザ情報
	 * @param userPasswordForm Formクラス
	 */
	public void updatePassword(LoginUser loginUser, UserPasswordForm userPasswordForm) {

		Optional<Account> userOptional = this.findById(loginUser);
		userOptional.ifPresent(user -> {
			user.setPassword(passwordEncoder.encode(userPasswordForm.getPassword()));
			user.setUpdatedAt(LocalDateNow.getLocalDateNow());
			this.userRepository.save(user);
		});
	}

	/**
	 * 性別更新処理
	 * @param loginUser      ログイン中のユーザ情報
	 * @param userGenderForm Formクラス
	 */
	public void updateGender(LoginUser loginUser, UserGenderForm userGenderForm) {

		Optional<Account> userOptional = this.findById(loginUser);
		userOptional.ifPresent(user -> {
			user.setGender(userGenderForm.getGender());
			user.setUpdatedAt(LocalDateNow.getLocalDateNow());
			this.userRepository.save(user);
		});
	}

	public void accountDelete(LoginUser loginUser) {

		Optional<Account> userOptional = this.findById(loginUser);
		userOptional.ifPresent(user -> {
			user.setPassword(null);
			user.setEmail(null);
			user.setDeletedAt(LocalDateNow.getLocalDateNow());
			user.setStatus(-1);
			this.userRepository.save(user);
		});
	}

}
