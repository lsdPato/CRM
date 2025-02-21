package com.tcs.crm.service;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class PasswordEcryptor {
    public String encrypt(String plainPassword) {
        byte[] passwordBytes = plainPassword.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(passwordBytes);
    }
}
