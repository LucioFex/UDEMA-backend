package Proyecto_UCEMA.UDEMA_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Proyecto_UCEMA.UDEMA_backend.models.Person;
import Proyecto_UCEMA.UDEMA_backend.repositories.PersonRepository;

@Service
public class PersonService {

	private final PersonRepository personRepository;
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<Person> getPeople() {
		return personRepository.findAll();
	}

	public void addNewPerson(Person person) {
		Optional<Person> personOptional = personRepository
			.findPersonByEmail(person.getEmail());
		
		if (personOptional.isPresent()) {
			throw new IllegalStateException("Email taken");
		}
		personRepository.save(person);
	}
}
