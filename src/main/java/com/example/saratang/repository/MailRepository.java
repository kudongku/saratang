package com.example.saratang.repository;

import com.example.saratang.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailRepository extends JpaRepository<Mail, Long> {
    Optional<Mail> findByEmail(String email);
}
