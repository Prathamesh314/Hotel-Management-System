package com.kurve.ratingservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;

@Document(value = "user_ratings")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    private String id;
    private Long userId;
    private Long hotelId;
    private int rating;
    private String feedback;
    private Hotel hotel;
}
