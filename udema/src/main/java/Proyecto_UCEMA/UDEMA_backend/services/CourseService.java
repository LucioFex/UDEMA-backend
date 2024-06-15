package Proyecto_UCEMA.UDEMA_backend.services;

import java.util.List;

import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.models.Student;
import Proyecto_UCEMA.UDEMA_backend.models.Class;

public interface CourseService {
	List<Course> getCourses();

	List<Student> getStudentsInCourse(Long courseId);

	List<Class> getClassesInCourse(Long courseId);

	void addNewCourse(Course course);

	void deleteCourse(Long courseId);

	void updateCourse(Long courseId, Course pCourse);

	void addStudent(Long courseId, Long studentId);

	void removeStudent(Long courseId, Long studentId);

	void addNewClass(Class pClass, Long courseId);

	void removeClass(Long courseId, Integer classNumber);
}
