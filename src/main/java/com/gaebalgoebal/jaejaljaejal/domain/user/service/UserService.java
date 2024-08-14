package com.gaebalgoebal.jaejaljaejal.domain.user.service;

import com.gaebalgoebal.jaejaljaejal.domain.user.Gender;
import com.gaebalgoebal.jaejaljaejal.domain.user.LoginMothod;
import com.gaebalgoebal.jaejaljaejal.domain.user.Role;
import com.gaebalgoebal.jaejaljaejal.domain.user.UserState;
import com.gaebalgoebal.jaejaljaejal.domain.user.dto.EmailDto;
import com.gaebalgoebal.jaejaljaejal.domain.user.dto.UserCreateDto;
import com.gaebalgoebal.jaejaljaejal.domain.user.entity.User;
import com.gaebalgoebal.jaejaljaejal.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    @Transactional(readOnly = true)
    public boolean checkEmail(EmailDto emailDto){
        return userRepository.existsByEmail(emailDto.getEmail());
    }

    @Transactional
    public void createUser(UserCreateDto user){
        User saveUser = User.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .password(passwordEncoder.encode(user.getPassword()))
                .cellPhoneNumber(user.getCellPhoneNumber())
                .birthDate(user.getBirthDate())
                .gender(user.getGender().equals(Gender.MALE.getKey()) ? Gender.MALE : Gender.FEMALE)
                .role(Role.USER)
                .userState(UserState.ACTIVE)
                .loginMothod(LoginMothod.EMAIL)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        userRepository.save(saveUser);
    }

    public String createVerityCode(){
        Random random = new Random();

        return random.ints(48, 123)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 | i >= 97))
                .limit(6)
                .collect(StringBuilder::new , StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Transactional
    public void sendVerityCodeEmail(EmailDto emailDto){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(emailDto.getEmail());
        simpleMailMessage.setSubject("재잘재잘 가입 인증번호");
        simpleMailMessage.setText("인증 번호는 " + createVerityCode() + " 입니다.");
        javaMailSender.send(simpleMailMessage);
    }
}
