package com.abc.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.abc.util.Validation;

public class BusColorValidator implements ConstraintValidator<BusColorConstraint, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return Validation.isBusColorValid(value);
	}

}
