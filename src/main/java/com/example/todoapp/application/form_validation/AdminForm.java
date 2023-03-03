package com.example.todoapp.application.form_validation;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AdminForm {

	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	private String username;

	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	@UniqueLogin(groups = ValidGroup3.class)
	private String email;

	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	private String emailconfirm;

	@NotBlank(groups = ValidGroup1.class)
	@Size(min = 8, max = 100, groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	private String password;

	@NotBlank(groups = ValidGroup1.class)
	@Size(min = 8, max = 100, groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	private String passwordconfirm;

	@NotNull(groups = ValidGroup1.class)
	private Integer age;

	@NotNull(groups = ValidGroup1.class)
	private Integer gender;

	@AssertTrue(message = "メールアドレス と メールアドレス再入力 は同一にしてください。", groups = ValidGroup3.class)
	public boolean isEmailValid() {
		if (email == null || email.isEmpty()) {
			return true;
		}

		return email.equals(emailconfirm);
	}

	@AssertTrue(message = "パスワード と パスワード再入力 は同一にしてください。", groups = ValidGroup3.class)
	public boolean isPasswordValid() {
		if (password == null || password.isEmpty()) {
			return true;
		}

		return password.equals(passwordconfirm);
	}

}
