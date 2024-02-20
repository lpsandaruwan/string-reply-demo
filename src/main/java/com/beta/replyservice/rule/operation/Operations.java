package com.beta.replyservice.rule.operation;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Operations {

    private String convertToMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public String toReverseString(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);
        return stringBuilder.reverse().toString();
    }

    public String toMd5String(String string) {
        return this.convertToMD5(string);
    }

    public String toUpperCase(String string) {
        return string.toUpperCase();
    }
}
