package com.cbstd.rssr.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.cbstd.rssr.annotation.UniqueUsername;
import com.cbstd.rssr.service.UserService;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	@Autowired
	UserService userService;
	
	@Override
	public void initialize(UniqueUsername constraintAnnotation) {
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(null == userService)
		{
			return true;
		}
		return null == userService.findByUsername(username);
	}

}
