package Proyecto_UCEMA.UDEMA_backend.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import Proyecto_UCEMA.UDEMA_backend.models.Class;
import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.repositories.ClassRepository;
import Proyecto_UCEMA.UDEMA_backend.repositories.CourseRepository;

@Configuration
public class ClassConfig {
	@Bean
	CommandLineRunner commandLineRunnerClass(ClassRepository classRepository, CourseRepository courseRepository) {
		return args -> { // Used for testing
			Course potionsBrewing = courseRepository.findByName("Potions Brewing");

			Class class1 = new Class(
				1,
				"A2",
				LocalDate.of(2024, 7, 25),
				potionsBrewing
			);

			Class class2 = new Class(
				2,
				"B4",
				LocalDate.of(2024, 8, 1),
				potionsBrewing
			);

			Class class3 = new Class(
				3,
				"C5",
				LocalDate.of(2024, 8, 8),
				potionsBrewing
			);

			Class class4 = new Class(
				4,
				"A2",
				LocalDate.of(2024, 8, 15),
				potionsBrewing
			);

			Class class5 = new Class(
				5,
				"B4",
				LocalDate.of(2024, 8, 22),
				potionsBrewing
			);

			Class class6 = new Class(
				6,
				"C5",
				LocalDate.of(2024, 8, 29),
				potionsBrewing
			);

			Class class7 = new Class(
				7,
				"A2",
				LocalDate.of(2024, 9, 5),
				potionsBrewing
			);

			Class class8 = new Class(
				8,
				"B4",
				LocalDate.of(2024, 9, 12),
				potionsBrewing
			);

			Class class9 = new Class(
				9,
				"C5",
				LocalDate.of(2024, 9, 19),
				potionsBrewing
			);

			Class class10 = new Class(
				10,
				"A2",
				LocalDate.of(2024, 9, 26),
				potionsBrewing
			);

			Class class11 = new Class(
				11,
				"B4",
				LocalDate.of(2024, 10, 3),
				potionsBrewing
			);

			Class class12 = new Class(
				12,
				"C5",
				LocalDate.of(2024, 10, 10),
				potionsBrewing
			);

			Class class13 = new Class(
				13,
				"A2",
				LocalDate.of(2024, 10, 17),
				potionsBrewing
			);

			Class class14 = new Class(
				14,
				"B4",
				LocalDate.of(2024, 10, 24),
				potionsBrewing
			);

			Class class15 = new Class(
				15,
				"C5",
				LocalDate.of(2024, 10, 31),
				potionsBrewing
			);

			Class class16 = new Class(
				16,
				"A2",
				LocalDate.of(2024, 11, 7),
				potionsBrewing
			);

			classRepository.saveAll(
				List.of(class1, class2, class3, class4, class5, class6, class7, class8, class9, class10, class11, class12, class13, class14, class15, class16)
			);
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
						.allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
}
