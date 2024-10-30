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

    @Column(length = 65535)
    private String description;

    @ManyToOne
    private User user;

    @Column(length = 65535)
    private String image;
    private boolean vegetarian;
    private LocalDateTime createdAt;
    @ElementCollection
    private List<Long> likes = new ArrayList<>();
}
