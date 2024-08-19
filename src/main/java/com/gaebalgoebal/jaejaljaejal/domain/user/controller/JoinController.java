package com.gaebalgoebal.jaejaljaejal.domain.user.controller;

import com.gaebalgoebal.jaejaljaejal.domain.user.dto.EmailDto;
import com.gaebalgoebal.jaejaljaejal.domain.user.dto.UserCreateDto;
import com.gaebalgoebal.jaejaljaejal.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class JoinController {

    private final UserService userService;

    @GetMapping("/email/check/")
    @CrossOrigin(origins="*")
    public ResponseEntity<String> checkEmail(@Valid EmailDto emailDto){
        if (userService.checkEmail(emailDto)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use.");
        }else {
            return ResponseEntity.ok("email duplicate check success!");
        }
    }

    @GetMapping("/nickname/check/{nickname}")
    @CrossOrigin(origins="*")
    public ResponseEntity<String> checkNickname(@PathVariable("nickname") String nickname){
        if (userService.checkNickname(nickname)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("nickname is already in use.");
        }else {
            return ResponseEntity.ok("nickname duplicate check success!");
        }
    }

    @PostMapping("/user/save")
    @CrossOrigin(origins="*")
    public ResponseEntity<String> signup(@RequestBody @Valid UserCreateDto userCreateDto){
        userService.createUser(userCreateDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("user sign up success!");
    }

    @PostMapping("/email/send")
    @CrossOrigin(origins="*")
    public ResponseEntity<String> sendMail(@RequestBody @Valid EmailDto emailDto){
        userService.sendVerityCodeEmail(emailDto);

        return ResponseEntity.ok("send verity code email");
    }

}