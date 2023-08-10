package com.kurve.UserService.service;

import com.kurve.UserService.dto.UserRequest;
import com.kurve.UserService.exception.ResourceNotFoundException;
import com.kurve.UserService.model.User;
import com.kurve.UserService.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    // add

    public void createUser(UserRequest userRequest){
        User user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .about(userRequest.getAbout())
                .build();
        userRepository.save(user);
        log.info("User saved successfully");
    }

    // delete
    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id: "+id));
        userRepository.delete(user);
    }

    // get

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id: "+id));
        return user;
    }

    // update

}
