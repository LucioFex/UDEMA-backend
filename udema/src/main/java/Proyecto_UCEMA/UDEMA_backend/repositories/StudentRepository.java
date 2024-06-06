package Proyecto_UCEMA.UDEMA_backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Proyecto_UCEMA.UDEMA_backend.models.Person;
import Proyecto_UCEMA.UDEMA_backend.models.Student;

@Repository
public interface StudentRepository
		extends JpaRepository<Student, Long> {

	Optional<Student> findStudentByEmail(String email);
	Optional<Person> findPersonByEmail(String email);

	// TODO: I've applied a workaround here to unlock this service.
	// The best idea would be to return List<Student> in the repository directly (but I keep getting errors).
	@Query(value="""
		SELECT p FROM Person p
		INNER JOIN course_students cs
			ON p.id = cs.students_id
		WHERE cs.course_id = :courseId
			AND p.person_type = 'STUDENT'
		""", nativeQuery=true)
	List<Person> findStudentsByCourseId(long courseId);
}
