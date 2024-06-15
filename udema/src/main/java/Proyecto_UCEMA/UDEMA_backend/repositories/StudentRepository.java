package Proyecto_UCEMA.UDEMA_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Proyecto_UCEMA.UDEMA_backend.models.Person;
import Proyecto_UCEMA.UDEMA_backend.models.Student;

@Repository
public interface StudentRepository
		extends JpaRepository<Student, Long> {

	Optional<Student> findStudentByEmail(String email);
	Optional<Person> findPersonByEmail(String email);
}
