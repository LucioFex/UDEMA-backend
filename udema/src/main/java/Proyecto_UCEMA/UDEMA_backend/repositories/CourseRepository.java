package Proyecto_UCEMA.UDEMA_backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.models.Professor;
import Proyecto_UCEMA.UDEMA_backend.models.Student;

@Repository
public interface CourseRepository
		extends JpaRepository<Course, Long> {
	Course findByName(String name);

	@Query("SELECT c.professor FROM Course c WHERE c.id = :courseId")
	Optional<Professor> findProfessorByCourseId(@Param("courseId") Long courseId);

	@Query("SELECT c.students FROM Course c WHERE c.id = :courseId")
	List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);
}
