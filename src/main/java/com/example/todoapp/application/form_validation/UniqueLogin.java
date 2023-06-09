package com.example.todoapp.application.form_validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueLoginValidator.class)
public @interface UniqueLogin {
	String message() default "このユーザ名、またはメールアドレスは既に登録されています";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
