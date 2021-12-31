package com.backbase.omdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class OmdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmdbApplication.class, args);
	}

}
