package com.grpcflix.movie.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

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
