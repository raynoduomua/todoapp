package com.example.todoapp.application.form_validation;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserEmailForm {

	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	@UniqueLogin(groups = ValidGroup3.class)
	private String email;

	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	private String emailconfirm;

	@AssertTrue(message = "メールアドレス と メールアドレス再入力 は同一にしてください。", groups = ValidGroup3.class)
	public boolean isEmailValid() {
		if (email == null || email.isEmpty()) {
			return true;
		}

		return email.equals(emailconfirm);
	}

}
