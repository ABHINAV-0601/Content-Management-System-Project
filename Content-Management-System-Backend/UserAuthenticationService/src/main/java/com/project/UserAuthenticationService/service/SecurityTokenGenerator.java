package com.project.UserAuthenticationService.service;

import com.project.UserAuthenticationService.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(User user);
}
