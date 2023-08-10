package com.kurve.ratingservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user_ratings")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    private Long id;
    private Long userId;
    private Long hotelId;
    private int rating;
    private String feedback;
}
