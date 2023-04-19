package com.project.UserAuthenticationService.service;

import com.project.UserAuthenticationService.domain.User;
import com.project.UserAuthenticationService.exception.UserAlreadyExistsException;
import com.project.UserAuthenticationService.exception.UserNotFoundException;
import com.project.UserAuthenticationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User addUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    @Override
    public User getByEmailAndPassword(String email, String password) throws UserNotFoundException {
        User user = userRepository.findByEmailAndPassword(email,password);
        if(user == null){
            throw new UserNotFoundException();
        }
        return user;
    }
}
