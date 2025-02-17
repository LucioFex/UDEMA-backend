package Proyecto_UCEMA.UDEMA_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto_UCEMA.UDEMA_backend.dto.CourseDTO;
import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.models.Professor;
import Proyecto_UCEMA.UDEMA_backend.models.Student;
import Proyecto_UCEMA.UDEMA_backend.services.CourseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "api/courses")
public class CourseController {
	private final CourseServiceImpl courseService;

	public CourseController(CourseServiceImpl courseService) {
		this.courseService = courseService;
	}

	// Course section

	@Operation(summary = "Get courses", description = "Get all courses")
	@GetMapping
	public List<Course> getCourses() {
		return courseService.getCourses();
	}

	@Operation(summary = "Get courses", description = "Get all courses")
	@GetMapping("{courseId}")
	public Course getCourse(@PathVariable("courseId") Long courseId) {
		return courseService.getCourse(courseId);
	}

	@Operation(summary = "Register new course", description = "Creates a new course")
	@PostMapping
	public void registerNewCourse(@RequestBody CourseDTO courseDTO) {
		courseService.addNewCourse(courseDTO);
	}

	@Operation(summary = "Delete course", description = "Delete a course by it's course ID")
	@DeleteMapping(path = "{courseId}")
	public void deleteCourse(@PathVariable("courseId") Long courseId) {
		courseService.deleteCourse(courseId);
	}

	@Operation(summary = "Update course", description = "Update an specific course general information by ID")
	@PutMapping(path = "{courseId}")
	public void updateCourse(
			@PathVariable("courseId") Long courseId, @RequestBody CourseDTO courseDTO) {
		courseService.updateCourse(courseId, courseDTO);
	}

	// Professor section

	@Operation(summary = "Get course professor", description = "Get the course professor")
	@GetMapping("{courseId}/professor")
	public Professor getProfessorByCourseId(@PathVariable("courseId") Long courseId) {
		return courseService.getProfessorByCourse(courseId);
	}

	@Operation(summary = "Add professor to course", description = "Add an existing professor to a certain course by the course ID and professor ID")
	@PostMapping("{courseId}/professor/{professorId}")
	public void addSProfessorToCourse(
			@PathVariable("courseId") Long courseId,
			@PathVariable("professorId") Long professorId ) {
		courseService.addProfessor(courseId, professorId);
	}

	@Operation(summary = "Remove professor from course", description = "Remove an existing professor of a certain course by the course ID and professor ID")
	@DeleteMapping("{courseId}/professor")
	public void removePrfoessorFromCourse(@PathVariable("courseId") Long courseId) {
		courseService.removeProfessor(courseId);
	}

	// Student section

	@Operation(summary = "Get students by course", description = "Get all the students in a course by it's course ID")
	@GetMapping("{courseId}/students")
	public List<Student> getStudentsByCourseId(@PathVariable("courseId") Long courseId) {
		return courseService.getStudentsInCourse(courseId);
	}

	@Operation(summary = "Add student to course", description = "Add an existing student to a certain course by the course ID and student ID")
	@PostMapping("{courseId}/students/{studentId}")
	public void addStudentToCourse(
			@PathVariable("courseId") Long courseId,
			@PathVariable("studentId") Long studentId ) {
		courseService.addStudent(courseId, studentId);
	}

	@Operation(summary = "Remove student from course", description = "Remove an existing student of a certain course by the course ID and student ID")
	@DeleteMapping("{courseId}/student/{studentId}")
	public void removeStudentFromCourse(
			@PathVariable("courseId") Long courseId,
			@PathVariable("studentId") Long studentId ) {
		courseService.removeStudent(courseId, studentId);
	}

	// Class section

}
