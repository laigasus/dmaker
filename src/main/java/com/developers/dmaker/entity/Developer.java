package com.developers.dmaker.entity;

import com.developers.dmaker.type.DeveloperLevel;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@SuppressWarnings("unused")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(EnumType.STRING)
    private DeveloperLevel developerLevel;

    @Enumerated(EnumType.STRING)
    private DeveloperSkillType developerSkillType;

    private Integer experienceYears;
    private String memberId;
    private String name;
    private Integer age;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    public static final class DeveloperBuilder {
        private Long id;
        private DeveloperLevel developerLevel;
        private DeveloperSkillType developerSkillType;
        private Integer experienceYears;
        private String memberId;
        private String name;
        private Integer age;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private DeveloperBuilder() {
        }

        public static DeveloperBuilder aDeveloper() {
            return new DeveloperBuilder();
        }

        public DeveloperBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DeveloperBuilder developerLevel(DeveloperLevel developerLevel) {
            this.developerLevel = developerLevel;
            return this;
        }

        public DeveloperBuilder developerSkillType(DeveloperSkillType developerSkillType) {
            this.developerSkillType = developerSkillType;
            return this;
        }

        public DeveloperBuilder experienceYears(Integer experienceYears) {
            this.experienceYears = experienceYears;
            return this;
        }

        public DeveloperBuilder memberId(String memberId) {
            this.memberId = memberId;
            return this;
        }

        public DeveloperBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DeveloperBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public DeveloperBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public DeveloperBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Developer build() {
            Developer developer = new Developer();
            developer.id = this.id;
            developer.updatedAt = this.updatedAt;
            developer.developerLevel = this.developerLevel;
            developer.memberId = this.memberId;
            developer.name = this.name;
            developer.experienceYears = this.experienceYears;
            developer.age = this.age;
            developer.createdAt = this.createdAt;
            developer.developerSkillType = this.developerSkillType;
            return developer;
        }
    }
}