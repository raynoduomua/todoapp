package com.example.todoapp.application.form_validation;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserPasswordForm {

	@NotBlank(groups = ValidGroup1.class)
	@Size(min = 8, max = 100, groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	private String password;

	@NotBlank(groups = ValidGroup1.class)
	@Size(min = 8, max = 100, groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	private String passwordconfirm;

	@AssertTrue(message = "パスワード と パスワード再入力 は同一にしてください。", groups = ValidGroup3.class)
	public boolean isPasswordValid() {
		if (password == null || password.isEmpty()) {
			return true;
		}

		return password.equals(passwordconfirm);
	}

}
