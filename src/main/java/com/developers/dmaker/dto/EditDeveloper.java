package com.developers.dmaker.dto;

import com.developers.dmaker.entity.Developer;
import com.developers.dmaker.entity.DeveloperSkillType;
import com.developers.dmaker.type.DeveloperLevel;
import io.soabase.recordbuilder.core.RecordBuilder;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("unused")
public class EditDeveloper {
    @RecordBuilder
    public record Request(
            @NotNull
            DeveloperLevel developerLevel,
            @NotNull
            DeveloperSkillType developerSkillType,
            @NotNull
            @Min(0)
            @Max(20)
            Integer experienceYears
    ) {
    }

    @RecordBuilder
    public record Response(
            DeveloperLevel developerLevel,
            DeveloperSkillType developerSkillType,
            Integer experienceYears,
            String memberId
    ) {
        public static CreateDeveloper.Response fromEntity(Developer developer) {
            return CreateDeveloperResponseBuilder.builder()
                    .developerLevel(developer.getDeveloperLevel())
                    .developerSkillType(developer.getDeveloperSkillType())
                    .experienceYears(developer.getExperienceYears())
                    .memberId(developer.getMemberId())
                    .build();
        }
    }
}
