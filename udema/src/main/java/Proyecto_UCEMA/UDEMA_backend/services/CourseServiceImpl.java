package Proyecto_UCEMA.UDEMA_backend.services;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import Proyecto_UCEMA.UDEMA_backend.dto.CourseDTO;
import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.models.Professor;
import Proyecto_UCEMA.UDEMA_backend.models.Student;
import Proyecto_UCEMA.UDEMA_backend.repositories.ClassRepository;
import Proyecto_UCEMA.UDEMA_backend.repositories.CourseRepository;
import Proyecto_UCEMA.UDEMA_backend.repositories.ProfessorRepository;
import Proyecto_UCEMA.UDEMA_backend.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CourseServiceImpl implements CourseService {
	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;
	private final ProfessorRepository professorRepository;

	public CourseServiceImpl(
		CourseRepository courseRepository,
		StudentRepository studentRepository,
		ClassRepository classRepository,
		ProfessorRepository professorRepository
	) {
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
		this.professorRepository = professorRepository;
	}

	// Course section

	@Override
	public List<Course> getCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course getCourse(Long courseId) {
		return courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found"));
	}

	@Override
	@Transactional
	public void addNewCourse(CourseDTO courseDTO) {
		Course course = new Course(courseDTO.getId(), courseDTO.getName(), courseDTO.getDescription());
		Student student;
		Professor professor;

		// Found students addition to the course
		for (Long studentId : courseDTO.getStudentIds()) {
			student = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student not found: " + studentId));
			course.addStudent(student);
		}

		// Professor addition
		professor = professorRepository.findById(courseDTO.getProfessorId()).orElseThrow(() -> new EntityNotFoundException("Professor not found: " + courseDTO.getProfessorId()));
		course.setProfessor(professor);

		// Course save
		courseRepository.save(course);
	}

	@Override
	@Transactional
	public void deleteCourse(Long courseId) {
		boolean exists = courseRepository.existsById(courseId);
		if (!exists) {
			throw new IllegalArgumentException(
				"Course with id " + courseId + " doesn\'t exist"
			);
		}
		courseRepository.deleteById(courseId);
	}

	@Override
	@Transactional
	public void updateCourse(Long courseId, CourseDTO pCourseDTO) {
		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new IllegalArgumentException(
				"The course with id " + courseId + " doesn\'t exist"
			));

		if (pCourseDTO.getName() != null && !Objects.equals(course.getName(), pCourseDTO.getName())) {
			course.setName(pCourseDTO.getName());
		}
		if (pCourseDTO.getDescription() != null && !Objects.equals(course.getDescription(), pCourseDTO.getDescription())) {
			course.setDescription(pCourseDTO.getDescription());
		}
	}

	// Professor section

	@Override
	public Professor getProfessorByCourse(Long courseId) {
		return courseRepository.findProfessorByCourseId(courseId).orElse(null);
	}

	@Override
	@Transactional
	public void addProfessor(Long courseId, Long professorId) {
		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new IllegalArgumentException("Course not found"));

		if (course.getProfessor() != null) {
			throw new IllegalStateException("There's already a professor in this course");
		}

		Professor professor = professorRepository.findById(professorId)
			.orElseThrow(() -> new IllegalArgumentException("Professor not found"));

		course.setProfessor(professor);
		courseRepository.save(course);
	}

	@Override
	@Transactional
	public void removeProfessor(Long courseId) {
		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new IllegalArgumentException("The course wasn\'t found"));
		course.removeProfessor();
		courseRepository.save(course);
	}

	// Student section

	@Override
	public List<Student> getStudentsInCourse(Long courseId) {
		return courseRepository.findStudentsByCourseId(courseId);
	}

	@Override
	@Transactional
	public void addStudent(Long courseId, Long studentId) {
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Course not found"));
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student not found"));

		course.addStudent(student);
		courseRepository.save(course);
	}

	@Override
	@Transactional
	public void removeStudent(Long courseId, Long studentId) {
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Course not found"));
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student not found"));

		course.removeStudent(student);
		courseRepository.save(course);
	}
}
