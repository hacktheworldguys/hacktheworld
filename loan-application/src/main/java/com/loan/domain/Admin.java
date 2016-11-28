package com.loan.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(indexes = {
        @Index(name = "idx_first_name_last_name", columnList = "firstName,lastName"),
        @Index(name = "idx_email", columnList = "email")})
@EqualsAndHashCode(callSuper = true)
@Data
public class Admin extends BaseEntity {

    private static final long serialVersionUID = 4510680420289546192L;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = true)
    private String password;

}
