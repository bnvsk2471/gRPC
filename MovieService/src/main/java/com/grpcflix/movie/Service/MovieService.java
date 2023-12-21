package com.grpcflix.movie.Service;

import com.grpcflix.movie.Entity.Movie;
import com.grpcflix.movie.Repository.MovieRepository;
import com.xNARA.grpcflix.movie.MovieDto;
import com.xNARA.grpcflix.movie.MovieSearchRequest;
import com.xNARA.grpcflix.movie.MovieSearchResponse;
import com.xNARA.grpcflix.movie.MovieServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class MovieService extends MovieServiceGrpc.MovieServiceImplBase {

    @Autowired
    private MovieRepository repository;

    @Override
    public void getMovies(MovieSearchRequest request, StreamObserver<MovieSearchResponse> responseObserver) {


        List<Movie> movieDtoList= this.repository.findByTitle(request.getGenre().toString());
        List<MovieDto> movieslist=movieDtoList.stream()
                .map(movie -> MovieDto.newBuilder()
                        .setTitle(movie.getTitle())
                        .setYear(movie.getYear())
                        .setRating(movie.getRating()).build()).collect(Collectors.toList());
        responseObserver.onNext(MovieSearchResponse.newBuilder().addAllMovie(movieslist).build());
        responseObserver.onCompleted();

    }
}
