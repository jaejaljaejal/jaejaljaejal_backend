package com.gaebalgoebal.jaejaljaejal.domain.user.entity;

import com.gaebalgoebal.jaejaljaejal.domain.user.Gender;
import com.gaebalgoebal.jaejaljaejal.domain.user.LoginMothod;
import com.gaebalgoebal.jaejaljaejal.domain.user.Role;
import com.gaebalgoebal.jaejaljaejal.domain.user.UserState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String cellPhoneNumber;

    @Column(name = "birth_date", nullable = true)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_state", nullable = false)
    private UserState userState;

    @Enumerated(EnumType.STRING)
    @Column(name = "login_method", nullable = false)
    private LoginMothod loginMothod;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "modified_date", nullable = false)
    private LocalDateTime modifiedDate;

    @Column(name = "term", nullable = false)
    private String term;

    @Builder
    public User(String email, String nickname, String password, String cellPhoneNumber, LocalDate birthDate, Gender gender, Role role, UserState userState, LoginMothod loginMothod, LocalDateTime createdDate, LocalDateTime modifiedDate, String term) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.birthDate = birthDate;
        this.gender = gender;
        this.role = role;
        this.userState = userState;
        this.loginMothod = loginMothod;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.term = term;
    }
}