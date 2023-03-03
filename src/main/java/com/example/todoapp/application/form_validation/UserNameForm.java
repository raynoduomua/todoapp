package com.example.todoapp.application.form_validation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserNameForm {

	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	private String username;

}
