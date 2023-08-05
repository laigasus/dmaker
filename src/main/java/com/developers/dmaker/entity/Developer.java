package com.developers.dmaker.entity;

import com.developers.dmaker.code.StatusCode;
import com.developers.dmaker.dto.EditDeveloper;
import com.developers.dmaker.type.DeveloperLevel;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
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

    @Enumerated(EnumType.STRING)
    private StatusCode statusCode;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public DeveloperLevel getDeveloperLevel() {
        return developerLevel;
    }

    public DeveloperSkillType getDeveloperSkillType() {
        return developerSkillType;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public Developer editDeveloper(EditDeveloper.Request request) {
        this.developerLevel = request.developerLevel();
        this.developerSkillType = request.developerSkillType();
        this.experienceYears = request.experienceYears();

        return this;
    }

    public static final class DeveloperBuilder {
        private Long id;
        private DeveloperLevel developerLevel;
        private DeveloperSkillType developerSkillType;
        private Integer experienceYears;
        private String memberId;
        private String name;
        private Integer age;
        private StatusCode statusCode;
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

        public DeveloperBuilder statusCode(StatusCode statusCode) {
            this.statusCode = statusCode;
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
            developer.createdAt = this.createdAt;
            developer.statusCode = this.statusCode;
            developer.name = this.name;
            developer.developerLevel = this.developerLevel;
            developer.experienceYears = this.experienceYears;
            developer.updatedAt = this.updatedAt;
            developer.memberId = this.memberId;
            developer.id = this.id;
            developer.developerSkillType = this.developerSkillType;
            developer.age = this.age;
            return developer;
        }
    }
}