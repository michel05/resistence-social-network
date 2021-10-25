package com.michel.projects.starwars.resistancesocialnetwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"com.michel.projects.starwars.resistancesocialnetwork.resources.repository"})
public class ResistanceSocialNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResistanceSocialNetworkApplication.class, args);
    }

}
