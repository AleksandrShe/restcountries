package org.example;

import org.example.fetcher.CountriesFetcher;
import org.example.repository.CountryRepository;
import org.example.models.Country;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @author Aleksandr
 */
@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CountryRepository repo) {
        return args -> {
            CountriesFetcher countriesFetcher = new CountriesFetcher();
            countriesFetcher.init();
            Country[] countries = countriesFetcher.fetch();
            Iterable it = List.of(countries);
            repo.saveAll(it);
        };
    }

}