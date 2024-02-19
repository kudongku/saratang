package com.example.saratang.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoder {
    public String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(text.getBytes());

        return bytesToHex(messageDigest.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
