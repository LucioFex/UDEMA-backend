package Proyecto_UCEMA.UDEMA_backend.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Proyecto_UCEMA.UDEMA_backend.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	Optional<Person> findByUsername(String username);
	Optional<Person> findPersonByEmail(String email);
}
