package com.example.saratang.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupRequestDto {
    private String username;
    private String password;
    private String email;
    private String emailVerifyKey;
}
