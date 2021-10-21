package com.nicolaspfeiffer.issuetracker.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AppUserPermission {
    PROJECT_READ("project:read"),
    PROJECT_WRITE("project:write");

    private final String permission;
}
