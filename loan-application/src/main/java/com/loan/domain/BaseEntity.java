package com.loan.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.loan.util.JsonJodaDateTimeSerializer;

import org.hibernate.annotations.DynamicUpdate;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@DynamicUpdate(value = true)
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -3001919008236514871L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonView(Views.Internal.class)
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private DateTime createdDate;

    @JsonView(Views.Internal.class)
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    @LastModifiedDate
    private DateTime modifiedDate;

}