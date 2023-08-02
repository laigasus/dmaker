package com.developers.dmaker.dto;

import com.developers.dmaker.entity.Developer;
import com.developers.dmaker.entity.DeveloperSkillType;
import com.developers.dmaker.type.DeveloperLevel;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record DeveloperDto(
        DeveloperLevel developerLevel,
        DeveloperSkillType developerSkillType,
        String memberId
) {
    public static DeveloperDto fromEntity(Developer developer) {
        return DeveloperDtoBuilder.builder()
                .developerLevel(developer.getDeveloperLevel())
                .developerSkillType(developer.getDeveloperSkillType())
                .memberId(developer.getMemberId()).build();
    }
}