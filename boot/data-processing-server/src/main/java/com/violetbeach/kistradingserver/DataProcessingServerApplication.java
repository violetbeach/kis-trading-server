package com.violetbeach.kistradingserver;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DataProcessingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataProcessingServerApplication.class, args);
	}

}
