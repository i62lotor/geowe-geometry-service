package org.geowe.service.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TopologyValidator.class)
@Documented
public @interface CheckTopology {

	String message() default "Topological Error";

	   Class<?>[] groups() default {};

	   Class<? extends Payload>[] payload() default {};
	   
	   int errorCode() default 001;	   
}
