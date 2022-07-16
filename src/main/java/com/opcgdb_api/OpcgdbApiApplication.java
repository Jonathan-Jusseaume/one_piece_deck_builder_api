package com.opcgdb_api;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class OpcgdbApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpcgdbApiApplication.class, args);
        log.info("API is launched");
    }

}
