package Proyecto_UCEMA.UDEMA_backend.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.repositories.CourseRepository;

@Configuration
public class CourseConfig {
	@Bean
	CommandLineRunner commandLineRunnerCourse(CourseRepository repository) {
		return args -> {
			Course potionsBrewing = new Course(
				"Potions Brewing", 
				"Discover the secrets of potion-making and their magical effects."
			);

			Course defenseAgainstTheDarkArts = new Course(
				"Defense Against the Dark Arts", 
				"Learn how to defend yourself against dark magic."
			);

			Course wizardChessStrategies = new Course(
				"Wizard Chess Strategies", 
				"Master the art of wizard chess."
			);

			Course transfigurationBasics = new Course(
				"Transfiguration Basics", 
				"Learn how to transform objects and beings with magic."
			);

			Course magicalCreaturesCare = new Course(
				"Magical Creatures Care", 
				"Understand and care for fantastic magical creatures."
			);

			Course historyOfMagic = new Course(
				"History of Magic", 
				"Explore the rich and fascinating history of the magical world."
			);

			Course charmsAndSpells = new Course(
				"Charms and Spells", 
				"Learn how to cast spells and enchant objects."
			);

			Course herbology = new Course(
				"Herbology", 
				"Study magical plants and their uses in wizardry."
			);

			Course quidditchTactics = new Course(
				"Quidditch Tactics", 
				"Improve your skills and strategies in the wizarding sport of Quidditch."
			);

			repository.saveAll(
				List.of(
					defenseAgainstTheDarkArts, wizardChessStrategies, potionsBrewing, 
					transfigurationBasics, magicalCreaturesCare, historyOfMagic, 
					charmsAndSpells, herbology, quidditchTactics
				)
			);
		};
	}
}
