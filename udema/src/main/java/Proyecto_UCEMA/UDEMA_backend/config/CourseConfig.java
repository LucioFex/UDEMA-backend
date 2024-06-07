package Proyecto_UCEMA.UDEMA_backend.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.repositories.CourseRepository;

@Configuration
public class CourseConfig {
	@Bean // TODO: Used for testing.
	CommandLineRunner commandLineRunnerCourse(CourseRepository repository) {
		return args -> {
			Course defenseAgainstTheDarkArts = new Course(
				"Defense Against the Dark Arts", 
				"Learn how to defend yourself against dark magic."
			);

			Course wizardChessStrategies = new Course(
				"Wizard Chess Strategies", 
				"Master the art of wizard chess."
			);

			repository.saveAll(
				List.of(defenseAgainstTheDarkArts, wizardChessStrategies)
			);
		};
	}
}
