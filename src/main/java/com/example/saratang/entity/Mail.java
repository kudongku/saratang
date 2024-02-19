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

    @Column(nullable = false)
    private String encodedEmail;

    public Mail(String mail, String encodedEmail) {
        this.email = mail;
        this.encodedEmail = encodedEmail;
    }
}
