package com.developers.dmaker.dto;

import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public record DevDto(
        @NonNull String firstName,
        @NonNull Integer age,
        Integer experienceYears,
        LocalDateTime startAt
) {

    public static final class DevDtoBuilder {
        private String firstName;
        private Integer age;
        private Integer experienceYears;
        private LocalDateTime startAt;

        private DevDtoBuilder() {
        }

        public static DevDtoBuilder aDevDto() {
            return new DevDtoBuilder();
        }

        public DevDtoBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public DevDtoBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public DevDtoBuilder experienceYears(Integer experienceYears) {
            this.experienceYears = experienceYears;
            return this;
        }

        public DevDtoBuilder startAt(LocalDateTime startAt) {
            this.startAt = startAt;
            return this;
        }

        public DevDto build() {
            return new DevDto(firstName, age, experienceYears, startAt);
        }
    }
}
