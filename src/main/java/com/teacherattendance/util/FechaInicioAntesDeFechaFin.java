package com.teacherattendance.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FechaInicioAntesDeFechaFinValidator.class)
public @interface FechaInicioAntesDeFechaFin {
    String message() default "{mob.concept.admin.models.constraint.DateRange.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String before();
    String after();
}
