package com.grpcflix.movie.Entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@ToString
public class Movie {
    @Id
    private Integer id;
    private String title;
    private Integer year;
    private double rating;
    private String genre;
}
