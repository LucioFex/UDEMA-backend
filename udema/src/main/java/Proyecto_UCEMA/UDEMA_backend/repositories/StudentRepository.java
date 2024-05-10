package Proyecto_UCEMA.UDEMA_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Proyecto_UCEMA.UDEMA_backend.models.Person;
import Proyecto_UCEMA.UDEMA_backend.models.Student;

@Repository
public interface StudentRepository
		extends JpaRepository<Student, Long> {

	@Query("SELECT s FROM Student s WHERE s.email = ?1")
	Optional<Student> findStudentByEmail(String email);

	@Query("SELECT p FROM Person p WHERE p.email = ?1")
	Optional<Person> findPersonByEmail(String email);
}
