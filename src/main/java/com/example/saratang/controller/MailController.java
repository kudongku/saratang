package com.example.saratang.controller;

import com.example.saratang.dto.CommonResponseDto;
import com.example.saratang.dto.MailRequestDto;
import com.example.saratang.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mails")
public class MailController {
    private final MailService mailService;

    @PostMapping
    public ResponseEntity<CommonResponseDto> verifyMail(@RequestBody MailRequestDto mailRequestDto) throws NoSuchAlgorithmException {
        return mailService.verifyMail(mailRequestDto);
    }
}
