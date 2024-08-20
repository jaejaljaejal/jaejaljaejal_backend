package com.gaebalgoebal.jaejaljaejal.domain.user.service;

import com.gaebalgoebal.jaejaljaejal.domain.user.dto.EmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final UserService userService;
    private final JavaMailSender javaMailSender;
    private final StringRedisTemplate redisTemplate;
    @Value("${spring.mail.email-auth-code-expiration-minute}")
    private int EXPIRATION_MINUTES;

    @Transactional
    public void sendVerityCodeEmail(EmailDto emailDto){
        String verityCode = userService.createVerityCode();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(emailDto.getEmail());
        simpleMailMessage.setSubject("재잘재잘 가입 인증번호");
        simpleMailMessage.setText("인증 번호는 " + verityCode + " 입니다.");
        redisTemplate.opsForValue().set(emailDto.getEmail(), verityCode, Duration.ofMinutes(EXPIRATION_MINUTES));
        javaMailSender.send(simpleMailMessage);
    }
}
