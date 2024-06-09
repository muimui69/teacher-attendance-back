package com.teacherattendance.util;

import com.teacherattendance.dto.PeriodoDTO;
import com.teacherattendance.entity.Periodo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.time.LocalDate;

public class FechaInicioAntesDeFechaFinValidator implements ConstraintValidator<FechaInicioAntesDeFechaFin, Object> {
    private String beforeFieldName;
    private String afterFieldName;

    @Override
    public void initialize(FechaInicioAntesDeFechaFin constraintAnnotation) {
        beforeFieldName = constraintAnnotation.before();
        afterFieldName = constraintAnnotation.after();
    }

    @Override
    public boolean isValid(final Object value, ConstraintValidatorContext context) {
        try {
            final Field beforeDateField = value.getClass().getDeclaredField(beforeFieldName);
            beforeDateField.setAccessible(true);

            final Field afterDateField = value.getClass().getDeclaredField(afterFieldName);
            afterDateField.setAccessible(true);

            final LocalDate beforeDate = (LocalDate) beforeDateField.get(value);
            final LocalDate afterDate = (LocalDate) afterDateField.get(value);
            return beforeDate.isEqual(afterDate) || beforeDate.isBefore(afterDate);

        } catch (NoSuchFieldException | IllegalAccessException ignored) {
            return false;
        }
    }
}
