package com.kurve.ratingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingRequest {
    private Long userId;
    private Long hotelId;
    private int rating;
    private String feedback;
}

