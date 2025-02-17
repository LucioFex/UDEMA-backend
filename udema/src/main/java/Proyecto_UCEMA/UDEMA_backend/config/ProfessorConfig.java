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
	@Bean
	CommandLineRunner commandLineRunnerProfessor(ProfessorRepository repository) {
		return args -> {
			Professor dumbledore = new Professor(
				"Albus",
				"Dumbledore",
				null,
				"albus.dumbledore@hogwarts.edu",
				LocalDate.of(1881, 8, 31),
				"fawkes-(user_not_accesible)",
				LocalDate.of(2019, 2, 22)
			);

			Professor snape = new Professor(
				"Severus",
				"Snape",
				null,
				"severus.snape@hogwarts.edu",
				LocalDate.of(1960, 1, 9),
				"sectumsempra-(user_not_accesible)",
				LocalDate.of(2021, 9, 3)
			);

			Professor mcgonagall = new Professor(
				"Minerva",
				"McGonagall",
				null,
				"minerva.mcgonagall@hogwarts.edu",
				LocalDate.of(1935, 10, 5),
				"transfiguration-(user_not_accesible)",
				LocalDate.of(2013, 3, 7)
			);

			Professor flitwick = new Professor(
				"Filius",
				"Flitwick",
				null,
				"filius.flitwick@hogwarts.edu",
				LocalDate.of(1958, 10, 17),
				"charmsmaster-(user_not_accesible)",
				LocalDate.of(2020, 6, 15)
			);

			Professor sprout = new Professor(
				"Pomona",
				"Sprout",
				null,
				"pomona.sprout@hogwarts.edu",
				LocalDate.of(1941, 5, 15),
				"herbology-(user_not_accesible)",
				LocalDate.of(2018, 11, 30)
			);

			Professor lupin = new Professor(
				"Remus",
				"Lupin",
				null,
				"remus.lupin@hogwarts.edu",
				LocalDate.of(1960, 3, 10),
				"moony-(user_not_accesible)",
				LocalDate.of(2017, 4, 29)
			);

			Professor trelawney = new Professor(
				"Sybill",
				"Trelawney",
				null,
				"sybill.trelawney@hogwarts.edu",
				LocalDate.of(1950, 3, 9),
				"divination-(user_not_accesible)",
				LocalDate.of(2019, 8, 21)
			);

			repository.saveAll(
				List.of(dumbledore, snape, mcgonagall, flitwick, sprout, lupin, trelawney)
			);
		};
	}
}
