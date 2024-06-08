package Proyecto_UCEMA.UDEMA_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.models.Student;
import Proyecto_UCEMA.UDEMA_backend.services.CourseServiceImpl;

@RestController
@RequestMapping(path = "api/courses")
public class CourseController {
	private final CourseServiceImpl courseService;

	public CourseController(CourseServiceImpl courseService) {
		this.courseService = courseService;
	}

	@GetMapping
	public List<Course> getCourses() {
		return courseService.getCourses();
	}

	@GetMapping("{courseId}/students")
	public List<Student> getStudentsByCourseId(@PathVariable("courseId") Long courseId) {
		return courseService.getStudentsInCourse(courseId);
	}

	@PostMapping
	public void registerNewCourse(@RequestBody Course course) {
		courseService.addNewCourse(course);
	}

	@PostMapping("{courseId}/students/{studentId}")
	public void addStudentToCourse(
		@PathVariable("courseId") Long courseId,
			@PathVariable("studentId") Long studentId ) {
		courseService.addStudent(courseId, studentId);
	}

	@DeleteMapping(path = "{courseId}")
	public void deleteCourse(@PathVariable("courseId") Long courseId) {
		courseService.deleteCourse(courseId);
	}

	@PutMapping(path = "{courseId}")
	public void updateCourse(
			@PathVariable("courseId") Long courseId,
			@RequestBody Course course) {
		courseService.updateCourse(courseId, course);
	}
}
