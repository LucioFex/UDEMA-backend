package Proyecto_UCEMA.UDEMA_backend.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Proyecto_UCEMA.UDEMA_backend.models.Class;
import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.models.Professor;
import Proyecto_UCEMA.UDEMA_backend.models.Student;
import Proyecto_UCEMA.UDEMA_backend.repositories.CourseRepository;
import Proyecto_UCEMA.UDEMA_backend.repositories.ProfessorRepository;
import Proyecto_UCEMA.UDEMA_backend.repositories.StudentRepository;
import Proyecto_UCEMA.UDEMA_backend.repositories.ClassRepository;
import jakarta.transaction.Transactional;

@Service
public class CourseServiceImpl implements CourseService {
	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;
	private final ClassRepository classRepository;
	private final ProfessorRepository professorRepository;

	public CourseServiceImpl(
		CourseRepository courseRepository,
		StudentRepository studentRepository,
		ClassRepository classRepository,
		ProfessorRepository professorRepository
	) {
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
		this.classRepository = classRepository;
		this.professorRepository = professorRepository;
	}

	// Course section

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

	// Professor section

	public Professor getProfessorByCourse(Long courseId) {
		return courseRepository.findProfessorByCourseId(courseId)
			.orElseThrow(() -> new IllegalStateException(
				"The course with id " + courseId + " doesn\'t have a professor selected"
			));
	}

	@Transactional
	public void addProfessor(Long courseId, Long professorId) {
		Optional<Course> courseOptional = courseRepository.findById(courseId);
		Optional<Professor> professorOptional = professorRepository.findById(professorId);
	
		if (courseOptional.isPresent() && professorOptional.isPresent()) {
			throw new RuntimeException("The course or student weren\'t found");
		}
		
		Course course = courseOptional.get();
		Professor professor = professorOptional.get();

		course.setProfessor(professor);
		courseRepository.save(course);
	}

	@Transactional
	public void removeProfessor(Long courseId) {
		Optional<Course> courseOptional = courseRepository.findById(courseId);

		if (courseOptional.isPresent()) {
			throw new RuntimeException("The course or professor weren\'t found");
		}

		Course course = courseOptional.get();
		course.removeProfessor();
		courseRepository.save(course);
	}

	// Student section

	public List<Student> getStudentsInCourse(Long courseId) {
		return courseRepository.findStudentsByCourseId(courseId);
	}

	@Transactional
	public void addStudent(Long courseId, Long studentId) {
		Optional<Course> courseOptional = courseRepository.findById(courseId);
		Optional<Student> studentOptional = studentRepository.findById(studentId);
	
		if (courseOptional.isPresent() && studentOptional.isPresent()) {
			throw new RuntimeException("The course or student weren\'t found");
		}
		
		Course course = courseOptional.get();
		Student student = studentOptional.get();

		course.addStudent(student);
		courseRepository.save(course);
	}

	@Transactional
	public void removeStudent(Long courseId, Long studentId) {
		Optional<Course> courseOptional = courseRepository.findById(courseId);
		Optional<Student> studentOptional = studentRepository.findById(studentId);

		if (courseOptional.isPresent() && studentOptional.isPresent()) {
			throw new RuntimeException("The course or student weren\'t found");
		}

		Course course = courseOptional.get();
		Student student = studentOptional.get();

		course.removeStudent(student);
		courseRepository.save(course);
	}

	// Class section

	public List<Class> getClassesInCourse(Long courseId) {
		return courseRepository.findClassessByCourseId(courseId);
	}

	@Transactional
	public void addNewClass(Class pClass, Long courseId) {
		Optional<Course> courseOptional = courseRepository.findById(courseId);
		if (!courseOptional.isPresent()) throw new RuntimeException("The course wasn\'t found");

		Integer lastClassNumber = 0;
		Optional<Class> topClass = courseRepository.findTopByCourseIdOrderByNumberDesc(courseId);
		if (topClass.isPresent()) lastClassNumber = topClass.get().getNumber();
		
		Course course = courseOptional.get();
		course.addSClass(pClass);
		pClass.setCourse(course);
		pClass.setNumber(lastClassNumber + 1);

		classRepository.save(pClass);
		courseRepository.save(course);
	}

	@Transactional
	public void removeClass(Long courseId, Integer classNumber) {
		Course course = courseRepository.findById(courseId)
			.orElseThrow(() -> new IllegalStateException(
				"Course with id " + courseId + " doesn\'t exist"
			));
		Class uClass = courseRepository.findClassByCourseIdAndNumber(courseId, classNumber)
			.orElseThrow(() -> new IllegalStateException(
				"Class with number " + classNumber + " not found in course " + course.getName()
			));
		
		course.removeClass(uClass);
		classRepository.deleteById(uClass.getId());
	}
}
