package com.nicolaspfeiffer.issuetracker.recaptcha;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class CaptchaServiceImpl implements CaptchaService {
    private static final String VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    private final RestTemplate restTemplate;
    @Value("${google.recaptcha.key.secret}")
    private String secret;

    @Override
    public Boolean isValidCaptcha(String captcha) {
        URI verifyUri = URI.create(String.format("%s?secret=%s&response=%s", VERIFY_URL, secret, captcha));
        CaptchaResponse response = restTemplate.postForObject(verifyUri, null, CaptchaResponse.class);
        return response.getSuccess();
    }
}
