package com.example.saratang.controller;

import com.example.saratang.dto.*;
import com.example.saratang.service.KakaoService;
import com.example.saratang.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final KakaoService kakaoService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto) throws NoSuchAlgorithmException {
        return userService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) throws NoSuchAlgorithmException {
        return userService.login(loginRequestDto, response);
    }

    @GetMapping("/kakao/callback")
    public String kakaoLogin(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException, NoSuchAlgorithmException {
        kakaoService.kakaoLogin(code, response);
        return "redirect:/";
    }
}
