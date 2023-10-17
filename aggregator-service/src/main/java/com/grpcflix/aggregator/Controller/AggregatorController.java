package com.grpcflix.aggregator.Controller;

import com.grpcflix.aggregator.Service.UserMovieService;
import com.grpcflix.aggregator.dto.RecommendedMovie;
import com.grpcflix.aggregator.dto.UserGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AggregatorController {

    @Autowired
    private UserMovieService userMovieService;

    @GetMapping("/user/{loginId}")
    public List<RecommendedMovie> getMovies(
            @PathVariable String loginId
    ){
        return userMovieService.getUserMovieSuggestions(loginId);
    }


    @PutMapping
    public void setUserGenre(@RequestBody UserGenre userGenre){

        this.userMovieService.setUserGenre(userGenre);

    }


}
