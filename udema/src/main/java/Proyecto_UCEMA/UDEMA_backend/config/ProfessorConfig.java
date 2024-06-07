package Proyecto_UCEMA.UDEMA_backend.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Proyecto_UCEMA.UDEMA_backend.models.Professor;
import Proyecto_UCEMA.UDEMA_backend.repositories.ProfessorRepository;

@Configuration
public class ProfessorConfig {
	@Bean // TODO: Used for testing.
	CommandLineRunner commandLineRunnerProfessor(ProfessorRepository repository) {
		return args -> {
			Professor dumbledore = new Professor(
				"Albus", 
				"Dumbledore", 
				"albus.dumbledore@hogwarts.edu", 
				LocalDate.of(1881, 8, 31), 
				"fawkes", 
				LocalDate.of(2024, 6, 1)
			);

			Professor snape = new Professor(
				"Severus", 
				"Snape", 
				"severus.snape@hogwarts.edu", 
				LocalDate.of(1960, 1, 9), 
				"sectumsempra", 
				LocalDate.of(2024, 6, 1)
			);

			Professor mcgonagall = new Professor(
				"Minerva", 
				"McGonagall", 
				"minerva.mcgonagall@hogwarts.edu", 
				LocalDate.of(1935, 10, 4), 
				"transfiguration", 
				LocalDate.of(2024, 6, 1)
			);

			repository.saveAll(
				List.of(dumbledore, snape, mcgonagall)
			);
		};
	}
}
