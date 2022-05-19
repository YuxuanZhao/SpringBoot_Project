package com.example.demo.task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JUNE;

@Configuration
public class TaskConfig {

    @Bean
    CommandLineRunner commandLineRunner(TaskRepository repository) {
        return args -> {
            Task ps = new Task("Problem Set", "Algorithm questions sheet", LocalDate.of(2022, JUNE, 1), true);
            Task pp = new Task("Programming Project", "Explore the results of different algorithm implementations", LocalDate.of(2022, JUNE, 5), false);
            Task fe = new Task("Final Exam", "Algorithm questions exam", LocalDate.of(2022, JUNE, 10), false);

            repository.saveAll(List.of(ps, pp, fe));
        };
    }
}
