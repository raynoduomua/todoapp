package com.example.todoapp.application.form_validation;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserGenderForm {

	@NotNull(groups = ValidGroup1.class)
	private Integer gender;

}
