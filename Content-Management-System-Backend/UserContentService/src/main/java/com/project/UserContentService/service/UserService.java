package com.project.UserContentService.service;

import com.project.UserContentService.domain.Content;
import com.project.UserContentService.domain.User;
import com.project.UserContentService.exception.ContentNotFoundException;
import com.project.UserContentService.exception.UserAlreadyExistsException;
import com.project.UserContentService.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    User addUser(User user) throws UserAlreadyExistsException;
    User addContentForUser(String email, Content content) throws UserNotFoundException;
    User deleteContentFromUser(String email,int contentId) throws UserNotFoundException, ContentNotFoundException;
    List<Content> getContentForUser(String email) throws UserNotFoundException;
    User updateContentForUser(String email,Content content) throws UserNotFoundException;

}
