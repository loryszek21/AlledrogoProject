package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReviewDTO {
    private Integer id;

    private String author;
    private Integer rating;
    private String description;
    private String reviewDate;

}
