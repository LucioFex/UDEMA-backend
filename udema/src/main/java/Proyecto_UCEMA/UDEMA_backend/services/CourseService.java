package Proyecto_UCEMA.UDEMA_backend.services;

import java.util.List;

import Proyecto_UCEMA.UDEMA_backend.dto.CourseDTO;
import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.models.Professor;
import Proyecto_UCEMA.UDEMA_backend.models.Student;

public interface CourseService {
	// Course section

	List<Course> getCourses();

	void addNewCourse(CourseDTO courseDTO);

	void deleteCourse(Long courseId);

	void updateCourse(Long courseId, CourseDTO pCourseDTO);

	// Professor section

	Professor getProfessorByCourse(Long courseId);

	void addProfessor(Long courseId, Long professorId);

	void removeProfessor(Long courseId);

	// Student section

	List<Student> getStudentsInCourse(Long courseId);

	void addStudent(Long courseId, Long studentId);

	void removeStudent(Long courseId, Long studentId);
}
