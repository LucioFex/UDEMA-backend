package Proyecto_UCEMA.UDEMA_backend.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Proyecto_UCEMA.UDEMA_backend.models.Student;
import Proyecto_UCEMA.UDEMA_backend.repositories.StudentRepository;

@Configuration
public class StudentConfig {
	@Bean // TODO: Used for testing.
	CommandLineRunner commandLineRunnerStudent(StudentRepository repository) {
		return args -> {
			Student harry = new Student(
				"Harry",
				"Potter",
				"harry.potter@hogwarts.edu",
				LocalDate.of(1980, 7, 31),
				"expelliarmus",
				LocalDate.of(2024, 6, 1),
				"Defense Against the Dark Arts"
			);

			Student hermione = new Student(
				"Hermione",
				"Granger",
				"hermione.granger@hogwarts.edu",
				LocalDate.of(1979, 9, 19),
				"leviosa",
				LocalDate.of(2024, 6, 1),
				"Arithmancy"
			);

			Student ron = new Student(
				"Ron",
				"Weasley",
				"ron.weasley@hogwarts.edu",
				LocalDate.of(1980, 3, 1),
				"chessmaster",
				LocalDate.of(2024, 6, 1),
				"Wizard Chess"
			);
			repository.saveAll(
				List.of(harry, hermione, ron)
			);
		};
	}
}
