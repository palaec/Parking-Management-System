package com.abc.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.abc.util.Validation;

public class BusTypeValidator implements ConstraintValidator<BusTypeConstraint, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return Validation.isBusTypeValid(value);
	}

}
