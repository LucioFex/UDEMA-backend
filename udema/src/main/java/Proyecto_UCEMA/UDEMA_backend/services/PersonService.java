package Proyecto_UCEMA.UDEMA_backend.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Proyecto_UCEMA.UDEMA_backend.models.Person;
import Proyecto_UCEMA.UDEMA_backend.repositories.PersonRepository;
import jakarta.transaction.Transactional;

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

	public void deletePerson(Long personId) {
		boolean exists = personRepository.existsById(personId);
		if (!exists) {
			throw new IllegalStateException(
				"Person with id " + personId + " doesn\'t exist"
			);
		}
		personRepository.deleteById(personId);
	}

	@Transactional
	public void updatePerson(Long personId, String name, String email) {
		// TODO: Some bussiness logic that I have to change personally later
		Person person = personRepository.findById(personId)
			.orElseThrow(() -> new IllegalStateException(
				"Person with id " + personId + " doesn\'t exist"
			));
		
		if (name != null && name.length() > 0 && !Objects.equals(person.getName(), name)) {
			person.setName(name);
		}

		if (email != null && email.length() > 0 && !Objects.equals(person.getEmail(), email)) {
			Optional<Person> personOptional = personRepository
				.findPersonByEmail(email);
			if (personOptional.isPresent()) {
				throw new IllegalStateException("Email '" + email + "' taken");
			}
			person.setEmail(email);
		}
	}
}
