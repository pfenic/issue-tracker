package com.nicolaspfeiffer.issuetracker.recaptcha;

public interface CaptchaService {
    Boolean isValidCaptcha(String captcha);
}
