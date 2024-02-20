package com.example.saratang.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column
    private String encodedEmail;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Mail(String mail, String encodedEmail) {
        this.email = mail;
        this.encodedEmail = encodedEmail;
    }

    public Mail(String email, User kakaoUser) {
        this.email = email;
        this.user = kakaoUser;
    }

    public void updateUser(User user) {
        this.user = user;
    }
}
