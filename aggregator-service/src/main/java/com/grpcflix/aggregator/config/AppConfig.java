package com.grpcflix.aggregator.config;

import com.grpcflix.aggregator.Service.UserMovieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //When @Service is not used in the service layer then we can use
    //Java base configuration
    @Bean
    public UserMovieService service(){
        return new UserMovieService();
    }
}
