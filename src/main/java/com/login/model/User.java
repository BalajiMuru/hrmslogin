package com.login.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "idbi_ess_nonad_users")
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "EMAIL_ID")
    private String emailId;

    @Column(name = "IS_ACTIVE")
    private Integer isActive;

    @Column(name = "ADDED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedOn;

    @Column(name = "USER_FULL_NAME")
    private String userFullName;

    @Column(name = "REPORTING1")
    private Long reporting1;

    @Column(name = "REPORTING2")
    private Long reporting2;

    @Column(name = "REPORTING3")
    private Long reporting3;

    @Column(name = "USER_ROLE")
    private String userRole;

    @Column(name = "PASSWORD")
    private String password;

}
