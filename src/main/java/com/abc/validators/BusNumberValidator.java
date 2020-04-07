package com.abc.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.abc.util.Validation;

public class BusNumberValidator implements ConstraintValidator<BusNumberConstraint, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return Validation.isBusNumberValid(value);
	}

}
