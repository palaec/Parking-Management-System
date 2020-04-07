package com.abc.validators;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = BusColorValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BusColorConstraint {
	String message() default "Invalid Bus Color . Bus Type can be only Orange OR Green";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
