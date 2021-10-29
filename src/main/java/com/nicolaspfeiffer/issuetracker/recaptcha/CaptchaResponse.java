package com.nicolaspfeiffer.issuetracker.recaptcha;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaResponse {
    private Boolean success;
    private String challenge_ts;
    private String hostname;
}
