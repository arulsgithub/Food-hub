package com.arulJD.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    private User user;
    private String image;
    private boolean vegetarian;
    private LocalDateTime createdAt;
    private List<Long> likes = new ArrayList<>();
}
