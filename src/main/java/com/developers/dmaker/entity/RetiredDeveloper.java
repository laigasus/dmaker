package com.developers.dmaker.entity;

import com.developers.dmaker.code.StatusCode;
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
public class RetiredDeveloper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    private String memberId;
    private String name;

    @Enumerated(EnumType.STRING)
    private StatusCode statusCode;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
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

    public static final class RetiredDeveloperBuilder {
        private Long id;
        private String memberId;
        private String name;
        private StatusCode statusCode;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private RetiredDeveloperBuilder() {
        }

        public static RetiredDeveloperBuilder aRetiredDeveloper() {
            return new RetiredDeveloperBuilder();
        }

        public RetiredDeveloperBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RetiredDeveloperBuilder memberId(String memberId) {
            this.memberId = memberId;
            return this;
        }

        public RetiredDeveloperBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RetiredDeveloperBuilder statusCode(StatusCode statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public RetiredDeveloperBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RetiredDeveloperBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public RetiredDeveloper build() {
            RetiredDeveloper retiredDeveloper = new RetiredDeveloper();
            retiredDeveloper.setStatusCode(statusCode);
            retiredDeveloper.name = this.name;
            retiredDeveloper.id = this.id;
            retiredDeveloper.updatedAt = this.updatedAt;
            retiredDeveloper.createdAt = this.createdAt;
            retiredDeveloper.memberId = this.memberId;
            return retiredDeveloper;
        }
    }
}