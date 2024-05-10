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
	/*@Bean TODO: Se mantiene para pruebas
	CommandLineRunner commandLineRunner(
			StudentRepository repository) {
		return args -> {
			Student severus = new Student(
				"Severus",
				"Snape",
				"severus.snape@gmail.com",
				LocalDate.of(1994, 11, 2),
				"expelliarmus",
				LocalDate.of(2022, 5, 7),
				"Ingeniería en Informática"
			);
			
			Student luna = new Student(
				"Luna",
				"Lovegood",
				"luna1lovegood@gmail.com",
				LocalDate.of(2004, 3, 30),
				"Wingardium",
				LocalDate.of(2019, 9, 1),
				"Ingeniería Industrial"
			);

			repository.saveAll(
				List.of(severus, luna)
			);
		};
	}*/
}
