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
	@Bean
	CommandLineRunner commandLineRunnerStudent(StudentRepository repository) {
		return args -> {
			Student harry = new Student(
				"Harry",
				"Potter",
				null, // HPotter
				"harry.potter@hogwarts.edu",
				LocalDate.of(1980, 7, 31),
				"expelliarmus-(user_not_accesible)",
				LocalDate.of(2023, 6, 1),
				"Defense Against the Dark Arts"
			);

			Student hermione = new Student(
				"Hermione",
				"Granger",
				null, // HGranger
				"hermione.granger@hogwarts.edu",
				LocalDate.of(1979, 9, 19),
				"leviosa-(user_not_accesible)",
				LocalDate.of(2022, 8, 5),
				"Arithmancy"
			);

			Student ron = new Student(
				"Ron",
				"Weasley",
				"test-RWeasley", // test-RWeasley
				"ron.weasley@hogwarts.edu",
				LocalDate.of(1980, 3, 1),
				"chessmaster-(user_not_accesible)",
				LocalDate.of(1997, 7, 15),
				"Wizard Chess"
			);

			Student draco = new Student(
				"Draco",
				"Malfoy",
				null, // DMalfoy
				"draco.malfoy@hogwarts.edu",
				LocalDate.of(1980, 6, 5),
				"serpensortia-(user_not_accesible)",
				LocalDate.of(2023, 7, 2),
				"Potions Brewing"
			);

			Student luna = new Student(
				"Luna",
				"Lovegood",
				null, // LLovegood
				"luna.lovegood@hogwarts.edu",
				LocalDate.of(1981, 2, 13),
				"wrackspurts-(user_not_accesible)",
				LocalDate.of(2023, 5, 10),
				"Care of Magical Creatures"
			);

			Student neville = new Student(
				"Neville",
				"Longbottom",
				null, // NLongbottom
				"neville.longbottom@hogwarts.edu",
				LocalDate.of(1980, 7, 30),
				"herbologyking-(user_not_accesible)",
				LocalDate.of(2023, 4, 20),
				"Herbology"
			);

			repository.saveAll(
				List.of(harry, hermione, ron, draco, luna, neville)
			);
		};
	}
}
