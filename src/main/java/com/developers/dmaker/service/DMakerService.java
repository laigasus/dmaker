package com.developers.dmaker.service;

import com.developers.dmaker.code.StatusCode;
import com.developers.dmaker.dto.CreateDeveloper;
import com.developers.dmaker.dto.DeveloperDetailDto;
import com.developers.dmaker.dto.DeveloperDto;
import com.developers.dmaker.dto.EditDeveloper;
import com.developers.dmaker.entity.Developer;
import com.developers.dmaker.entity.RetiredDeveloper;
import com.developers.dmaker.exception.DMakerException;
import com.developers.dmaker.repository.DeveloperRepository;
import com.developers.dmaker.repository.RetiredDeveloperRepository;
import com.developers.dmaker.type.DeveloperLevel;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.developers.dmaker.constant.DMakerConstant.MAX_JUNIOR_EXPERIENCE_YEARS;
import static com.developers.dmaker.constant.DMakerConstant.MIN_SENIOR_EXPERIENCE_YEARS;
import static com.developers.dmaker.exception.DMakerErrorCode.*;

@Service
public class DMakerService {
    private final DeveloperRepository developerRepository;
    private final RetiredDeveloperRepository retiredDeveloperRepository;

    public DMakerService(
            DeveloperRepository developerRepository,
            RetiredDeveloperRepository retiredDeveloperRepository
    ) {
        this.developerRepository = developerRepository;
        this.retiredDeveloperRepository = retiredDeveloperRepository;
    }

    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {
        validateCreateDeveloperRequest(request);

        return CreateDeveloper.Response.fromEntity(
                developerRepository.save(createDeveloperFromRequest(request))
        );
    }

    private Developer createDeveloperFromRequest(CreateDeveloper.Request request) {
        return Developer.DeveloperBuilder.aDeveloper()
                .developerLevel(request.developerLevel())
                .developerSkillType(request.developerSkillType())
                .experienceYears(request.experienceYears())
                .memberId(request.memberId())
                .statusCode(StatusCode.EMPLOYED)
                .name(request.name())
                .age(request.age())
                .build();
    }



    private void validateCreateDeveloperRequest(@NonNull CreateDeveloper.Request request) {
        // business validation
        validateDeveloperLevel(request.developerLevel(), request.experienceYears());

        developerRepository.findByMemberId(request.memberId()).ifPresent((developer -> {
            throw new DMakerException(DUPLICATED_MEMBER_ID);
        }));
    }

    private static void validateDeveloperLevel(DeveloperLevel developerLevel, Integer experienceYears) {
        boolean isInvalid = switch (developerLevel) {
            case SENIOR -> experienceYears < MIN_SENIOR_EXPERIENCE_YEARS;
            case JUNGNIOR -> experienceYears < MAX_JUNIOR_EXPERIENCE_YEARS || experienceYears > MIN_SENIOR_EXPERIENCE_YEARS;
            case JUNIOR -> experienceYears > MAX_JUNIOR_EXPERIENCE_YEARS;
            case NEW -> false;
        };

        if (isInvalid) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
    }

    @Transactional(readOnly = true)
    public List<DeveloperDto> getAllDevelopers() {
        return developerRepository.findAll()
                .stream().map(DeveloperDto::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public DeveloperDetailDto getDeveloperDetail(String memberId) {
        return DeveloperDetailDto.fromEntity(getDeveloperByMemberId(memberId));
    }

    private Developer getDeveloperByMemberId(String memberId) {
        return developerRepository.findByMemberId(memberId).orElseThrow(
                () -> new DMakerException(NO_DEVELOPER)
        );
    }

    @Transactional
    public DeveloperDetailDto editDeveloper(String memberId, EditDeveloper.Request request) {
        validateDeveloperLevel(request.developerLevel(), request.experienceYears());

        return DeveloperDetailDto.fromEntity(
                getDeveloperByMemberId(memberId).editDeveloper(request)
        );
    }

    @Transactional
    public DeveloperDetailDto deleteDeveloper(String memberId) {
        Developer developer = developerRepository.findByMemberId(memberId).orElseThrow(
                () -> new DMakerException(NO_DEVELOPER)
        );
        developer.setStatusCode(StatusCode.RETIRED);

        developerRepository.deleteByMemberId(memberId);
        retiredDeveloperRepository.save(
                RetiredDeveloper.RetiredDeveloperBuilder.aRetiredDeveloper()
                        .memberId(memberId)
                        .name(developer.getName())
                        .build()
        );
        return DeveloperDetailDto.fromEntity(developer);
    }
}