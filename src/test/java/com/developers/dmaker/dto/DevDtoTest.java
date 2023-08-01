package com.developers.dmaker.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

class DevDtoTest {
    static final String firstName = "snow";
    static final Integer age = 21;
    static final LocalDateTime startAt = LocalDateTime.now();
    static final Integer experienceYears = 3;

    @Test
    @DisplayName("값이 모두 있는지 확인")
    void valueInsertedFully() throws IllegalAccessException {
        DevDto devDto = DevDto.DevDtoBuilder.aDevDto().firstName(firstName).age(age).startAt(startAt).experienceYears(experienceYears).build();
        Field[] fields = devDto.getClass().getDeclaredFields();

        for (var field : fields) {
            field.setAccessible(true);
            Assertions.assertNotNull(field.get(devDto), "해당 값이 들어가지 않았습니다>" + field.getName());
        }
    }
}