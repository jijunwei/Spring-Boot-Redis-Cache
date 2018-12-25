package com.springboot.config.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 *  on 2017/09/24.
 */
final class IntegerToEnumConverterFactory implements ConverterFactory<String, Enum> {
    IntegerToEnumConverterFactory() {
    }

    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        Class<?> enumType = targetType;
        while (enumType != null && !enumType.isEnum()) {
            enumType = enumType.getSuperclass();
        }

        if (enumType == null) {
            throw new IllegalArgumentException("The target type " + targetType.getName() + " does not refer to an enum");
        } else {
            return new IntegerToEnum(enumType);
        }
    }

    private class IntegerToEnum<T extends Enum> implements Converter<String, T> {
        private final Class<T> enumType;

        public IntegerToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String source) {
            T[] ts = enumType.getEnumConstants();
            int ordinal = Integer.parseInt(source);
            return ordinal < ts.length && ordinal >= 0 ? ts[ordinal] : null;
        }
    }
}