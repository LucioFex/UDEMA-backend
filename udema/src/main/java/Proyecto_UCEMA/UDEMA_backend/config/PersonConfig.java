package Proyecto_UCEMA.UDEMA_backend.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Proyecto_UCEMA.UDEMA_backend.models.Person;
import Proyecto_UCEMA.UDEMA_backend.repositories.PersonRepository;

@Configuration
public class PersonConfig {
	@Bean
	CommandLineRunner commandLineRunner(
			PersonRepository repository) {
		return args -> {
			Person severus = new Person(
				"Severus",
				"Snape",
				"severus.snape@gmail.com",
				LocalDate.of(1994, 11, 2),
				"expelliarmus"
			);
			
			Person luna = new Person(
				"Luna",
				"Lovegood",
				"luna1lovegood@gmail.com",
				LocalDate.of(2004, 3, 30),
				"Wingardium"
			);

			repository.saveAll(
				List.of(severus, luna)
			);
		};
	}
}
