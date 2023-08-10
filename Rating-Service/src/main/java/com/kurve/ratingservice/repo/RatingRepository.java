package com.kurve.ratingservice.repo;

import com.kurve.ratingservice.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating,Long> {
    List<Rating> findByUserId(Long id);
    List<Rating> findByHotelId(Long id);
}
