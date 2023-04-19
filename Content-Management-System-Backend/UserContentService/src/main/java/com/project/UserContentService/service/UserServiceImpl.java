package com.project.UserContentService.service;

import com.project.UserContentService.domain.Content;
import com.project.UserContentService.domain.User;
import com.project.UserContentService.exception.ContentNotFoundException;
import com.project.UserContentService.exception.UserAlreadyExistsException;
import com.project.UserContentService.exception.UserNotFoundException;
import com.project.UserContentService.proxy.UserProxy;
import com.project.UserContentService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProxy userProxy;
    @Override
    public User addUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        User user1 = userRepository.save(user);
        if(!(user1.getEmail().isEmpty())){
            ResponseEntity responseEntity = userProxy.saveUser(user);
            System.out.println(responseEntity.getBody());
        }
        return user1;
    }

    @Override
    public User addContentForUser(String email, Content content) throws UserNotFoundException {
        if(userRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(email).get();
        if(user.getContentList() == null){
            user.setContentList(Arrays.asList(content));
        }else{
            List<Content> contents = user.getContentList();
            contents.add(content);
            user.setContentList(contents);
        }
        return userRepository.save(user);
    }

    @Override
    public User deleteContentFromUser(String email, int contentId) throws UserNotFoundException, ContentNotFoundException {
        boolean result = false;
        if(userRepository.findById(email).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(email).get();
        List<Content> contents = user.getContentList();
        result = contents.removeIf(obj->obj.getContentId()==contentId);
        if(!result)
        {
            throw new ContentNotFoundException();
        }
        user.setContentList(contents);
        return userRepository.save(user);
    }

    @Override
    public List<Content> getContentForUser(String email) throws UserNotFoundException {
        if(userRepository.findById(email).isEmpty())
        {
            throw new UserNotFoundException();
        }
        return userRepository.findById(email).get().getContentList();
    }

    @Override
    public User updateContentForUser(String email, Content content) throws UserNotFoundException {
        if(userRepository.findById(email).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(email).get();
        List<Content> contents = user.getContentList();
        Iterator<Content> iterator = contents.iterator();
        while (iterator.hasNext()){
            Content content1 = iterator.next();
            if(content1.getContentId() == content.getContentId()){

                content1.setTitle(content.getTitle());
                content1.setBody(content.getBody());
            }
        }
        user.setContentList(contents);
        return userRepository.save(user);
    }


}
