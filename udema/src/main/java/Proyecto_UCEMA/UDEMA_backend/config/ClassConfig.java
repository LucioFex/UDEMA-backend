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
		return args -> { // TODO: Used for testing
			Course potions = new Course(
				"Potions",
				"Learn how to brew various magical potions."
			);
			courseRepository.save(potions);

			// Crear clases y asociarlas con el curso
			Class class1 = new Class(
				1,
				"A2",
				LocalDate.of(2024, 7, 25),
				potions
			);
			Class class2 = new Class(
				2,
				"B3",
				LocalDate.of(2024, 7, 26),
				potions
			);

			classRepository.saveAll(
				List.of(class1, class2)
			);
		};
	}

	// TODO: Seguir averiguando porqué no se permite el acceso a peticiones POST desde el front para 'class' (problema de CORS), mientras
	// que si se puede leer, borrar, etc... Y el resto de clases tampoco tienen problemas de CORS.
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
