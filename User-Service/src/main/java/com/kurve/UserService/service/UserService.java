package com.kurve.UserService.service;

import com.kurve.UserService.dto.UserRequest;
import com.kurve.UserService.exception.ResourceNotFoundException;
import com.kurve.UserService.model.Rating;
import com.kurve.UserService.model.User;
import com.kurve.UserService.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

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
        ArrayList<Rating> ratings = restTemplate.getForObject("http://localhost:8083/api/rating/user/" + id, ArrayList.class);
        user.setRatings(ratings);
        return user;
    }

    // update

}
