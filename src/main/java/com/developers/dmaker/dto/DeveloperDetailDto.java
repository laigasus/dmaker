package com.developers.dmaker.dto;

import com.developers.dmaker.entity.Developer;
import com.developers.dmaker.entity.DeveloperSkillType;
import com.developers.dmaker.type.DeveloperLevel;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record DeveloperDetailDto(
        DeveloperLevel developerLevel,
        DeveloperSkillType developerSkillType,
        Integer experienceYears,
        String memberId,
        String name,
        Integer age
) {
    public static DeveloperDetailDto fromEntity(Developer developer) {
        return DeveloperDetailDtoBuilder.builder()
                .developerLevel(developer.getDeveloperLevel())
                .developerSkillType(developer.getDeveloperSkillType())
                .experienceYears(developer.getExperienceYears())
                .memberId(developer.getMemberId())
                .name(developer.getName())
                .age(developer.getAge())
                .build();
    }
}
