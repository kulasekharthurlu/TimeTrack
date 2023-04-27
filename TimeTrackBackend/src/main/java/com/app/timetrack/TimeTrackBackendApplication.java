package com.app.timetrack;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class TimeTrackBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(TimeTrackBackendApplication.class, args);
	}
	 @Bean
	  public ModelMapper modelMapper() {
	    return new ModelMapper();
	  }
}
 
