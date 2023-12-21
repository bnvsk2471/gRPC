package com.grpcflix.aggregator.Service;

import com.grpcflix.aggregator.dto.RecommendedMovie;
import com.grpcflix.aggregator.dto.UserGenre;
import com.xNARA.grpcflix.movie.Genre;
import com.xNARA.grpcflix.movie.MovieSearchRequest;
import com.xNARA.grpcflix.movie.MovieSearchResponse;
import com.xNARA.grpcflix.movie.MovieServiceGrpc;
import com.xNARA.grpcflix.user.UserGenreUpdateRequest;
import com.xNARA.grpcflix.user.UserResponse;
import com.xNARA.grpcflix.user.UserSearchRequest;
import com.xNARA.grpcflix.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class UserMovieService {
    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userStub;

    @GrpcClient("movie-service")
    private MovieServiceGrpc.MovieServiceBlockingStub movieStub;

    public List<RecommendedMovie> getUserMovieSuggestions(String loginId){
        UserSearchRequest userSearchRequest=
                UserSearchRequest.newBuilder().setLoginId(loginId).build();
        UserResponse userResponse= this.userStub.getUserGenre(userSearchRequest);
        MovieSearchRequest movieSearchRequest=
                MovieSearchRequest.newBuilder().setGenre(userResponse.getGenre()).build();
        MovieSearchResponse movieSearchResponse= this.movieStub.getMovies(movieSearchRequest);
        return movieSearchResponse.getMovieList()
                .stream()
                .map(movieDto -> new RecommendedMovie(movieDto.getTitle(), movieDto.getYear(), movieDto.getRating()))
                .collect(Collectors.toList());
    }

    public void setUserGenre(UserGenre userGenre){

        UserGenreUpdateRequest userGenreUpdateRequest= UserGenreUpdateRequest.newBuilder()
                .setLoginId(userGenre.getLoginId())
                .setGenre(Genre.valueOf(userGenre.getGenre().toUpperCase()))
                .build();
        UserResponse userResponse=this.userStub.updateUserGenre(userGenreUpdateRequest);
    }

}
