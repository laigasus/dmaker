package com.developers.dmaker.service;

import com.developers.dmaker.entity.Developer;
import com.developers.dmaker.entity.DeveloperSkillType;
import com.developers.dmaker.repository.DeveloperRepository;
import com.developers.dmaker.type.DeveloperLevel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DMakerService {
    private final DeveloperRepository developerRepository;

    public DMakerService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Transactional
    public void createDeveloper() {
        Developer developer = Developer.DeveloperBuilder.aDeveloper()
                .developerLevel(DeveloperLevel.NEW)
                .developerSkillType(DeveloperSkillType.BACK_END)
                .experienceYears(1)
                .name("옥재욱")
                .age(26)
                .build();

        developerRepository.save(developer);
    }
}