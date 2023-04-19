package com.project.UserAuthenticationService.controller;

import com.project.UserAuthenticationService.domain.User;
import com.project.UserAuthenticationService.exception.UserAlreadyExistsException;
import com.project.UserAuthenticationService.exception.UserNotFoundException;
import com.project.UserAuthenticationService.service.SecurityTokenGenerator;
import com.project.UserAuthenticationService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/userdata")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;
    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User user) throws UserAlreadyExistsException {
        ResponseEntity responseEntity = null;
        try{
            responseEntity = new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        }catch (UserAlreadyExistsException e){
            throw new UserAlreadyExistsException();
        }catch(Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {
        Map<String,String> map = null;
        try{
            User user1 = userService.getByEmailAndPassword(user.getEmail(), user.getPassword());
            if(user1.getEmail().equals(user.getEmail())){
                map = securityTokenGenerator.generateToken(user);
            }
            return new ResponseEntity<>(map,HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
