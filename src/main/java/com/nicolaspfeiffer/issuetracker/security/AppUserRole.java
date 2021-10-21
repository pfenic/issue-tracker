package com.nicolaspfeiffer.issuetracker.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.nicolaspfeiffer.issuetracker.security.AppUserPermission.PROJECT_READ;
import static com.nicolaspfeiffer.issuetracker.security.AppUserPermission.PROJECT_WRITE;

@Getter
@RequiredArgsConstructor
public enum AppUserRole {
    USER(Set.of(PROJECT_READ)),
    ADMIN(Set.of(PROJECT_READ, PROJECT_WRITE));

    private final Set<AppUserPermission> permissions;
}
