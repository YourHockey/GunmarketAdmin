package ru.vakoom.gunmarket.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EntityScan("ru.gunmarket.model")
public class StartAppDatabaseFiller {

    public static void main(String[] args) {
        SpringApplication.run(StartAppDatabaseFiller.class);
    }

}
