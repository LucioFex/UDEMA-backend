package Proyecto_UCEMA.UDEMA_backend.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.models.Student;
import Proyecto_UCEMA.UDEMA_backend.repositories.CourseRepository;
import Proyecto_UCEMA.UDEMA_backend.repositories.StudentRepository;
import jakarta.transaction.Transactional;

@Service
public class CourseServiceImpl implements CourseService {
	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;

	public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
	}

	public List<Course> getCourses() {
		return courseRepository.findAll();
	}

	public List<Student> getStudentsInCourse(Long courseId) {
		return courseRepository.findStudentsByCourseId(courseId);
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
	public void addStudent(Long courseId, Long studentId) {
		Optional<Course> courseOptional = courseRepository.findById(courseId);
		Optional<Student> studentOptional = studentRepository.findById(studentId);
	
		if (courseOptional.isPresent() && studentOptional.isPresent()) {
			Course course = courseOptional.get();
			Student student = studentOptional.get();

			course.addStudent(student);
			courseRepository.save(course);
		} else {
			throw new RuntimeException("The course or student weren\'t found");
		}
	}
}
