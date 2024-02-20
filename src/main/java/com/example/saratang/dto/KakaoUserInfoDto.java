package com.example.saratang.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfoDto {
    private Long id;
    private String nickname;
    private String email;
    private String profileImage;

    public KakaoUserInfoDto(Long id, String nickname, String email, String profileImage) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
    }
}
