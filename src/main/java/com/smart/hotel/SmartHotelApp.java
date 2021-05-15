package com.smart.hotel;

import com.smart.hotel.config.properties.ApplicationProperties;
import com.smart.hotel.config.properties.ComponentControllerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties({ LiquibaseProperties.class, ApplicationProperties.class, ComponentControllerProperties.class})
public class SmartHotelApp {

    public static void main(String[] args) {
        SpringApplication.run(SmartHotelApp.class, args);
    }
}
