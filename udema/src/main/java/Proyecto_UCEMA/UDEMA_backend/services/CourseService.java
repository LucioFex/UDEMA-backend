package Proyecto_UCEMA.UDEMA_backend.services;

import java.util.List;

import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.models.Professor;
import Proyecto_UCEMA.UDEMA_backend.models.Student;
import Proyecto_UCEMA.UDEMA_backend.models.Class;

public interface CourseService {
	// Course section

	List<Course> getCourses();

	void addNewCourse(Course course);

	void deleteCourse(Long courseId);

	void updateCourse(Long courseId, Course pCourse);

	// Professor section

	Professor getProfessorByCourse(Long courseId);

	void addProfessor(Long courseId, Long professorId);
	
	void removeProfessor(Long courseId);

	// Student section

	List<Student> getStudentsInCourse(Long courseId);

	void addStudent(Long courseId, Long studentId);

	void removeStudent(Long courseId, Long studentId);

	// Class section

	List<Class> getClassesInCourse(Long courseId);

	void addNewClass(Class pClass, Long courseId);

	void removeClass(Long courseId, Integer classNumber);
}
