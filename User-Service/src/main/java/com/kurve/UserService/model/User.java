package com.kurve.UserService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_user")
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String about;
    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
