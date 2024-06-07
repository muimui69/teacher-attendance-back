package com.teacherattendance.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

public class ModelMapperTransform {

    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
    }

    private ModelMapperTransform() {}

    public static <S, T> void map(S source, T target) {
        modelMapper.map(source, target);
    }

}
