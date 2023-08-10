package com.kurve.ratingservice.controller;

import com.kurve.ratingservice.dto.RatingRequest;
import com.kurve.ratingservice.model.Rating;
import com.kurve.ratingservice.repo.RatingRepository;
import com.kurve.ratingservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
@RequiredArgsConstructor
public class Controller {

    private final RatingService ratingService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody RatingRequest ratingRequest){
        ratingService.createRating(ratingRequest);
        return "Rating Saved";
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Rating> getALl()
    {
        return ratingService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rating getRatingById(@PathVariable Long id){
        return ratingService.getRatingById(id);
    }

    @GetMapping("/user/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Rating> getByUser(@PathVariable Long userID)
    {
        return ratingService.getRatingByUser(userID);
    }

    @GetMapping("/hotel/{hotelID}")
    @ResponseStatus(HttpStatus.OK)
    public List<Rating> getByHotel(@PathVariable Long hotelID)
    {
        return ratingService.getRatingByHotel(hotelID);
    }
}
