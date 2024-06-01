package Proyecto_UCEMA.UDEMA_backend.services;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.models.Student;
import Proyecto_UCEMA.UDEMA_backend.repositories.CourseRepository;
import jakarta.transaction.Transactional;

@Service
public class CourseService {
	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public List<Course> getCourses() {
		return courseRepository.findAll();
	}

	public void addNewCourse(Course course) {
		courseRepository.save(course);
	}

	public void deleteCourse(Long courseId) {
		boolean exists = courseRepository.existsById(courseId);
		if (!exists) {
			throw new IllegalStateException(
				"Course with id " + courseId + " doesn\'t exist"
			);
		}
		courseRepository.deleteById(courseId);
	}

	@Transactional
	public void updateCourse(Long courseId, Course pCourse) {
		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new IllegalStateException(
				"The course with id " + courseId + " doesn\'t exist"
			));
		
		if (pCourse.getName() != null && !Objects.equals(course.getName(), pCourse.getName())) {
			course.setName(pCourse.getName());
		}
		if (pCourse.getDescription() != null && !Objects.equals(course.getDescription(), pCourse.getDescription())) {
			course.setDescription(pCourse.getDescription());
		}
	}

	@Transactional
	public void addStudent(String courseName, Student pStudent) {
		Course course = courseRepository.findByName(courseName);
		course.addStudent(pStudent);
	}
}
