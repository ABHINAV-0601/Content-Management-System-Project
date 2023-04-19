package com.project.UserContentService.controller;

import com.project.UserContentService.domain.Content;
import com.project.UserContentService.domain.User;
import com.project.UserContentService.exception.ContentNotFoundException;
import com.project.UserContentService.exception.UserAlreadyExistsException;
import com.project.UserContentService.exception.UserNotFoundException;
import com.project.UserContentService.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping("/contentdata")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User user) throws UserAlreadyExistsException {
        ResponseEntity responseEntity = null;
        try{
            user.setContentList(new ArrayList<>());
            responseEntity = new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        }catch (UserAlreadyExistsException e){
            throw new UserAlreadyExistsException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @PostMapping("/content/addContent/{email}")
    public ResponseEntity<?> addProductForUser(@PathVariable String email, @RequestBody Content content) throws UserNotFoundException {
        ResponseEntity responseEntity = null;
        try{
            responseEntity = new ResponseEntity<>(userService.addContentForUser(email,content), HttpStatus.CREATED);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("/contents/{email}")
    public ResponseEntity<?> getContentsForUser(@PathVariable String email) throws UserNotFoundException {
        ResponseEntity responseEntity = null;
        try{
            responseEntity = new ResponseEntity<>(userService.getContentForUser(email), HttpStatus.OK);
        }catch(UserNotFoundException e){
            throw new UserNotFoundException();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @DeleteMapping("/content/deleteContent/{email}/{contentId}")
    public ResponseEntity<?> deleteProductForUser(@PathVariable int contentId,@PathVariable String email) throws ContentNotFoundException, UserNotFoundException {
        ResponseEntity responseEntity = null;
        try{
            responseEntity = new ResponseEntity<>(userService.deleteContentFromUser(email, contentId), HttpStatus.OK);
        }catch (ContentNotFoundException e){
            throw new ContentNotFoundException();
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        } catch(Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @PutMapping("/content/updateContent/{email}")
    public ResponseEntity<?> updateTrackForUser(@PathVariable String email,@RequestBody Content content) throws UserNotFoundException {
        ResponseEntity responseEntity = null;
        try{
            responseEntity = new ResponseEntity<>(userService.updateContentForUser(email,content), HttpStatus.CREATED);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
