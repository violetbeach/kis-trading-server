package com.violetbeach;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataProcessingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataProcessingServerApplication.class, args);
	}

}
