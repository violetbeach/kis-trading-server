package com.violetbeach.kistradingserver;

import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NoArgsConstructor
class DataProcessingServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataProcessingServerApplication.class, args);
    }

}
