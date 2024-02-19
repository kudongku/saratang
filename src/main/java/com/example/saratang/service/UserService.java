package com.example.saratang.service;

import com.example.saratang.dto.CommonResponseDto;
import com.example.saratang.dto.LoginRequestDto;
import com.example.saratang.dto.SignupRequestDto;
import com.example.saratang.entity.Mail;
import com.example.saratang.entity.User;
import com.example.saratang.jwt.JwtUtil;
import com.example.saratang.repository.MailRepository;
import com.example.saratang.repository.UserRepository;
import com.example.saratang.util.Encoder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MailRepository mailRepository;
    private final JwtUtil jwtUtil;
    private final Encoder encoder;

    public ResponseEntity<CommonResponseDto> signup(SignupRequestDto signupRequestDto) throws NoSuchAlgorithmException {
        String username = signupRequestDto.getUsername();
        String encodedPassword = encoder.encrypt(signupRequestDto.getPassword());
        String email = signupRequestDto.getEmail();
        String emailVerifyKey = signupRequestDto.getEncodedEmail();

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("동일한 아이디가 존재합니다.");
        }

        Mail mail = mailRepository.findByEmail(email).orElseThrow(
                () -> new NullPointerException("메일 인증이 이루어지지 않았습니다.")
        );

        if (mail.getUser() != null) {
            throw new IllegalArgumentException("이미 가입된 메일입니다.");
        }

        if (!mail.getEncodedEmail().equals(emailVerifyKey)) {
            throw new IllegalArgumentException("인증키가 올바르지 않습니다.");
        }

        User user = new User(signupRequestDto, encodedPassword);
        mail.updateUser(user);
        userRepository.save(user);

        return ResponseEntity.status(200).body(new CommonResponseDto("회원가입 성공", 200));
    }

    public ResponseEntity<CommonResponseDto> login(LoginRequestDto loginRequestDto, HttpServletResponse res) throws NoSuchAlgorithmException {
        User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow(
                () -> new NullPointerException("가입된 적 없는 아이디입니다.")
        );

        if (!encoder.encrypt(loginRequestDto.getPassword()).equals(user.getPassword())) {
            return ResponseEntity.status(400).body(new CommonResponseDto("비밀번호가 올바르지 않습니다.", 400));
        }

        jwtUtil.addToken(user.getUsername(), res);

        return ResponseEntity.status(200).body(new CommonResponseDto("로그인 성공", 200));
    }

}
