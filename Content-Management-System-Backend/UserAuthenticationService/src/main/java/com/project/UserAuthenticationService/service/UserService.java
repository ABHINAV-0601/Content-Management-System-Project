package com.project.UserAuthenticationService.service;

import com.project.UserAuthenticationService.domain.User;
import com.project.UserAuthenticationService.exception.UserAlreadyExistsException;
import com.project.UserAuthenticationService.exception.UserNotFoundException;

public interface UserService {
    User addUser(User user) throws UserAlreadyExistsException;
    User getByEmailAndPassword(String email,String password) throws UserNotFoundException;
}
