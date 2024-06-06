package Proyecto_UCEMA.UDEMA_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Proyecto_UCEMA.UDEMA_backend.models.Course;
// import Proyecto_UCEMA.UDEMA_backend.models.Student;

@Repository
public interface CourseRepository
		extends JpaRepository<Course, Long> {

	Course findByName(String name);
}
