package com.developers.dmaker.dto;

import com.developers.dmaker.entity.Developer;
import com.developers.dmaker.entity.DeveloperSkillType;
import com.developers.dmaker.type.DeveloperLevel;
import io.soabase.recordbuilder.core.RecordBuilder;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;

public class CreateDeveloper {
    @RecordBuilder
    public record Request(
            @NotNull
            DeveloperLevel developerLevel,
            @NotNull
            DeveloperSkillType developerSkillType,
            @NotNull
            @Min(0)
            @Max(20)
            Integer experienceYears,
            @NotNull
            @Size(min = 3, max = 20, message = "memberId size only 3~50")
            String memberId,
            @NotNull
            @Size(min = 3, max = 20, message = "name size only 3~20")
            String name,
            @Min(18)
            Integer age
    ) {
    }

    @RecordBuilder
    public record Response(
            DeveloperLevel developerLevel,
            DeveloperSkillType developerSkillType,
            Integer experienceYears,
            String memberId
    ) {
        public static Response fromEntity(@NonNull Developer developer) {
            return CreateDeveloperResponseBuilder.builder()
                    .developerLevel(developer.getDeveloperLevel())
                    .developerSkillType(developer.getDeveloperSkillType())
                    .experienceYears(developer.getExperienceYears())
                    .memberId(developer.getMemberId())
                    .build();
        }
    }
}
