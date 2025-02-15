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
			Course potionsCourse = new Course(
				"PotionsCourse",
				"Learn how to brew various magical potionsCourse."
			);
			courseRepository.save(potionsCourse);
			
			Course charmsCourse = new Course(
				"CharmsCourse",
				"Master the art of spellcasting and learn various magical charms."
			);
			courseRepository.save(charmsCourse);

			// Crear clases y asociarlas con el curso
			Class class1 = new Class(
				1,
				"A2",
				LocalDate.of(2024, 7, 25),
				potionsCourse
			);
			Class class2 = new Class(
				2,
				"B3",
				LocalDate.of(2024, 7, 26),
				charmsCourse
			);

			classRepository.saveAll(
				List.of(class1, class2)
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
