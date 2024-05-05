package Proyecto_UCEMA.UDEMA_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Proyecto_UCEMA.UDEMA_backend.models.Person;
import Proyecto_UCEMA.UDEMA_backend.services.PersonService;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping(path = "api/people")
public class PersonController {
	private final PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping
	public List<Person> getPeople() {
		return personService.getPeople();
	}

	@PostMapping
	public void registerNewPerson(@RequestBody Person person) {
		personService.addNewPerson(person);
	}

	@DeleteMapping(path = "{personId}")
	public void deletePerson(@PathVariable("personId") Long personId) {
		personService.deletePerson(personId);
	}

	@PutMapping("{personId}")
	public void updatePerson(
			@PathVariable("personId") Long personId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
		personService.updatePerson(personId, name, email);
	}
}
