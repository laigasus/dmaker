package com.developers.dmaker.dto;

import com.developers.dmaker.dto.DevDto.DevDtoBuilder;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DevDtoTest {
    static String firstName = "snow";
    static Integer age = 21;
    static LocalDateTime startAt = LocalDateTime.now();
    static Integer experienceYears = 3;

    @Test
    @DisplayName("값이 모두 있는지 확인")
    void valueInsertedFully() throws IllegalAccessException {
        DevDto devDto = DevDtoBuilder.aDevDto().firstName(firstName).age(age).startAt(startAt).experienceYears(experienceYears).build();
        Field[] fields = devDto.getClass().getDeclaredFields();

        for(var field : fields) {
            field.setAccessible(true);
            Assertions.assertNotNull(field.get(devDto), "해당 값이 들어가지 않았습니다>" + field.getName());
        }
    }
}