package com.globomantics.plots;

import com.globomantics.plots.models.Plot;
import com.globomantics.plots.repository.PlotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Arrays;

@SpringBootApplication
@EnableRetry
public class PlotsApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlotsApplication.class);

    @Bean
    public ApplicationRunner runner(PlotRepository plotRepository) {
        return args -> {
            Plot plot1 = new Plot(1, "Prakash", "Rice", 450, ZonedDateTime.now(Clock.systemUTC()));
            Plot plot2 = new Plot(2, "Agarwal", "Cotton", 150, ZonedDateTime.now(Clock.systemUTC()));
            Plot plot3 = new Plot(3, "DJ", "Maize", 300, ZonedDateTime.now(Clock.systemUTC()));

            plotRepository.saveAll(Arrays.asList(plot1, plot2, plot3));

            plotRepository.findAll().forEach(plot -> LOGGER.info(plot.toString()));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(PlotsApplication.class, args);
    }
}
