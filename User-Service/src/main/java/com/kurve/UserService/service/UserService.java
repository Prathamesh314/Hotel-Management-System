package com.kurve.UserService.service;

import com.kurve.UserService.dto.UserRequest;
import com.kurve.UserService.exception.ResourceNotFoundException;
import com.kurve.UserService.model.Hotel;
import com.kurve.UserService.model.Rating;
import com.kurve.UserService.model.User;
import com.kurve.UserService.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public void deleteUser(String id){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id: "+id));
        userRepository.delete(user);
    }

    // get

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(String id){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id: "+id));
        Rating[] ratingOfUsers = restTemplate.getForObject("http://localhost:8083/api/rating/user/" + id, Rating[].class);
        List<Rating> ratings = Arrays.stream(ratingOfUsers).toList();
        List<Rating> newratings = ratings.stream().map(
                rating -> {
                    //http://localhost:8082/api/hotel/1
                    Hotel hotel = restTemplate.getForObject("http://localhost:8082/api/hotel/" + rating.getHotelId(), Hotel.class);
                    rating.setHotel(hotel);
                    return rating;
                }
        ).collect(Collectors.toList());
        user.setRatings(newratings);
        return user;
    }

    // update

}
