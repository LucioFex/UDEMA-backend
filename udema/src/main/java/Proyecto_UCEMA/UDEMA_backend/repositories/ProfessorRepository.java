package Proyecto_UCEMA.UDEMA_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Proyecto_UCEMA.UDEMA_backend.models.Person;
import Proyecto_UCEMA.UDEMA_backend.models.Professor;

@Repository
public interface ProfessorRepository
		extends JpaRepository<Professor, Long> {
	@Query("SELECT p FROM Professor p WHERE p.email = ?1")
	Optional<Professor> findProfessorByEmail(String email);

	@Query("SELECT p FROM Person p WHERE p.email = ?1")
	Optional<Person> findPersonByEmail(String email);
}