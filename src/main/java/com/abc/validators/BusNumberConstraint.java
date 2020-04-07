package com.abc.validators;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = BusNumberValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BusNumberConstraint {
	String message() default "Invalid bus number";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
