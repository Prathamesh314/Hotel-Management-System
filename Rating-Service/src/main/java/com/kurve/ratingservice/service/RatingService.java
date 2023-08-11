package com.kurve.ratingservice.service;

import com.kurve.ratingservice.dto.RatingRequest;
import com.kurve.ratingservice.exception.ResourceNotFoundException;
import com.kurve.ratingservice.model.Rating;
import com.kurve.ratingservice.repo.RatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingService {

   private final RatingRepository ratingRepository;

   public void createRating(RatingRequest ratingRequest){
      Rating rating = Rating.builder()
              .userId(ratingRequest.getUserId())
              .hotelId(ratingRequest.getHotelId())
              .rating(ratingRequest.getRating())
              .feedback(ratingRequest.getFeedback())
              .build();

      ratingRepository.save(rating);
      log.info("Rating Saved");
   }

   public List<Rating> getAll(){
      return ratingRepository.findAll();
   }

   public Rating getRatingById(String id){
      Rating rating = ratingRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Rating with Id: "+id));
      return rating;
   }

   public List<Rating> getRatingByUser(Long userID){
      List<Rating> ratings = ratingRepository.findByUserId(userID);
      return ratings;
   }

   public List<Rating> getRatingByHotel(Long hotelId){
      return ratingRepository.findByHotelId(hotelId);
   }

}
