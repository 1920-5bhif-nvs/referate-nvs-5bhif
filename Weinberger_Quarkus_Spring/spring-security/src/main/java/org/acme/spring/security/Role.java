package org.acme.spring.security;

import org.springframework.stereotype.Component;

@Component
public class Role {
    public final String ADMIN = "admin";
    public final String USER = "user";
    public final String PREMIUM_USER = "premium_user";
}
