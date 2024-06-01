package Proyecto_UCEMA.UDEMA_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.models.Student;

@Repository
public interface  CourseRepository
		extends JpaRepository<Course, Long> {

	Course findByName(String name);

	@Query("SELECT p FROM course c INNER JOIN person p ON c.student_id = p.id AND p.person_type = 'STUDENT'")
	Optional<Student> findStudentById(String id);
}
