package com.abc.validators;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = BusTypeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BusTypeConstraint {
	String message() default "Invalid Bus Type . Bus Type can be only Regular OR Doubledecker";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
