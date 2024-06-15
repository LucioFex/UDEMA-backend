package Proyecto_UCEMA.UDEMA_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.models.Student;
import Proyecto_UCEMA.UDEMA_backend.models.Class;
import Proyecto_UCEMA.UDEMA_backend.services.CourseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "api/courses")
public class CourseController {
	private final CourseServiceImpl courseService;

	public CourseController(CourseServiceImpl courseService) {
		this.courseService = courseService;
	}

	@Operation(summary = "Get courses", description = "Get all courses")
	@GetMapping
	public List<Course> getCourses() {
		return courseService.getCourses();
	}

	@Operation(summary = "Get students by course", description = "Get all the students in a course by it's course ID")
	@GetMapping("{courseId}/students")
	public List<Student> getStudentsByCourseId(@PathVariable("courseId") Long courseId) {
		return courseService.getStudentsInCourse(courseId);
	}

	@Operation(summary = "Get course classes", description = "Get all the classes in a course by it's course ID")
	@GetMapping("{courseId}/classes")
	public List<Class> getClassesByCourseId(@PathVariable("courseId") Long courseId) {
		return courseService.getClassesInCourse(courseId);
	}

	@Operation(summary = "Add student to course", description = "Add an existing student to a certain course by the course ID and student ID")
	@PostMapping("{courseId}/students/{studentId}")
	public void addStudentToCourse(
			@PathVariable("courseId") Long courseId,
			@PathVariable("studentId") Long studentId ) {
		courseService.addStudent(courseId, studentId);
	}

	@Operation(summary = "Add new class", description = "Create a new class and save it in a specific course by the course ID")
	@PostMapping("{courseId}/classes")
	public void addNewClass(
			@PathVariable("courseId") Long courseId,
			@RequestBody Class pClass ) {
		courseService.addNewClass(pClass, courseId);
	}

	@Operation(summary = "Register new course", description = "Creates a new course")
	@PostMapping
	public void registerNewCourse(@RequestBody Course course) {
		courseService.addNewCourse(course);
	}

	@Operation(summary = "Delete course", description = "Delete a course by it's course ID")
	@DeleteMapping(path = "{courseId}")
	public void deleteCourse(@PathVariable("courseId") Long courseId) {
		courseService.deleteCourse(courseId);
	}

	@Operation(summary = "Delete class by number", description = "Delete a class by it's course class number (not ID), in a specific course, by the course ID and class number")
	@DeleteMapping(path = "{courseId}/classes/{classNumber}")
	public void deleteCourseClassByNumber(
		@PathVariable("courseId") Long courseId,
			@PathVariable("classNumber") Integer classNumber) {
		courseService.removeClass(courseId, classNumber);
	}

	@Operation(summary = "Update course", description = "Update an specific course by ID")
	@PutMapping(path = "{courseId}")
	public void updateCourse(
			@PathVariable("courseId") Long courseId,
			@RequestBody Course course) {
		courseService.updateCourse(courseId, course);
	}
}
