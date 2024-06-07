package Proyecto_UCEMA.UDEMA_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.models.Student;

@Repository
public interface CourseRepository
		extends JpaRepository<Course, Long> {

	Course findByName(String name);

	@Query("SELECT c.students FROM Course c WHERE c.id = :courseId")
	List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);
}
