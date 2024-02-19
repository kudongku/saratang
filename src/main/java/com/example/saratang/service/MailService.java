package com.example.saratang.service;

import com.example.saratang.dto.CommonResponseDto;
import com.example.saratang.dto.MailRequestDto;
import com.example.saratang.entity.Mail;
import com.example.saratang.repository.MailRepository;
import com.example.saratang.util.Encoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class MailService {
    private static final String senderEmail = "kudongku@gmail.com";
    private final JavaMailSender javaMailSender;
    private final MailRepository mailRepository;
    private final Encoder encoder;

    public ResponseEntity<CommonResponseDto> verifyMail(MailRequestDto mailRequestDto) throws NoSuchAlgorithmException {
        String title = "[사라탕] 가입 인증 메일입니다.";
        String contentHeader = "인증번호는\n";
        String contentFooter = "\n입니다.";
        String encodedEmail = encoder.encrypt(mailRequestDto.getMail());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailRequestDto.getMail());
        message.setFrom(senderEmail);
        message.setSubject(title);
        message.setText(contentHeader + encodedEmail + contentFooter);

        javaMailSender.send(message);
        mailRepository.save(new Mail(mailRequestDto.getMail(), encodedEmail));
        return ResponseEntity.status(200).body(new CommonResponseDto("가입 이메일 전송 성공", 200));
    }
}
