package com.kurve.ratingservice.service;

import com.kurve.ratingservice.dto.RatingRequest;
import com.kurve.ratingservice.exception.ResourceNotFoundException;
import com.kurve.ratingservice.model.Rating;
import com.kurve.ratingservice.repo.RatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingService {

   private final RatingRepository ratingRepository;

   public void createRating(RatingRequest ratingRequest){
      String randomid = UUID.randomUUID().toString();

      Rating rating = Rating.builder()
              .id(randomid)
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

   public List<Rating> getRatingByUser(String userID){
      List<Rating> ratings = ratingRepository.findByUserId(userID);
      return ratings;
   }

   public List<Rating> getRatingByHotel(String hotelId){
      return ratingRepository.findByHotelId(hotelId);
   }

}
