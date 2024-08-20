package com.gaebalgoebal.jaejaljaejal.domain.user.service;

import com.gaebalgoebal.jaejaljaejal.domain.user.*;
import com.gaebalgoebal.jaejaljaejal.domain.user.dto.EmailDto;
import com.gaebalgoebal.jaejaljaejal.domain.user.dto.UserCreateDto;
import com.gaebalgoebal.jaejaljaejal.domain.user.entity.User;
import com.gaebalgoebal.jaejaljaejal.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    private final StringRedisTemplate redisTemplate;


    @Transactional(readOnly = true)
    public boolean checkEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean checkNickname(String nickname){
        return userRepository.existsByNickname(nickname);
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
                .term(YesNo.YES)
                .build();

        userRepository.save(saveUser);
    }

    public String createVerityCode(){
        Random random = new Random();

        return random.ints(48, 123)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(6)
                .collect(StringBuilder::new , StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public boolean verifyCode(EmailDto emailDto){
        String redisVerifyCode = redisTemplate.opsForValue().get(emailDto.getEmail());
        if (redisVerifyCode != null && redisVerifyCode.equals(emailDto.getVerityCode())){
            redisTemplate.delete(emailDto.getEmail());
            return true;
        }
        return false;
    }
}
