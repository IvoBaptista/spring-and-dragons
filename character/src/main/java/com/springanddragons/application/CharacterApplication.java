package com.springanddragons.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(value = "com.springanddragons", excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = InitializeApplication.class)})
@EntityScan(value = {"com.springanddragons", "org.axonframework.eventhandling.tokenstore",
		"org.axonframework.modelling.saga.repository.jpa",
		"org.axonframework.eventsourcing.eventstore.jpa"})
@EnableJpaRepositories(value = "com.springanddragons")
@Slf4j
public class CharacterApplication {

    public static void main(String[] args) {
        log.info("Starting server");
        SpringApplication.run(CharacterApplication.class, args);
    }

}
