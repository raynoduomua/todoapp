package com.example.todoapp.application.form_validation;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TodoForm {

	private Integer id;

	@NotBlank(groups = ValidGroup1.class)
	private String contents;

}
