package com.example.todoapp.application.form_validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.todoapp.infrastructure.repository.user.UserRepository;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

	private final UserRepository userRepository;

	public UniqueLoginValidator() {
		this.userRepository = null;
	}

	@Autowired
	public UniqueLoginValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return userRepository == null || userRepository.findByEmail(value).isEmpty();
	}
}
