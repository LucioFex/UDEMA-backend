package Proyecto_UCEMA.UDEMA_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.services.CourseService;

@RestController
@RequestMapping(path = "api/courses")
public class CourseController {
	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping
	public List<Course> getCourses() {
		return courseService.getCourses();
	}

	@PostMapping
	public void registerNewCourse(@RequestBody Course course) {
		courseService.addNewCourse(course);
	}

	@DeleteMapping(path = "{courseId}")
	public void deleteCourse(@PathVariable("courseId") Long courseId) {
		courseService.deleteCourse(courseId);
	}

	@PutMapping(path = "{courseId}")
	public void updateCourse(
			@PathVariable("courseId") Long courseId,
			@RequestBody Course course) {
		courseService.deleteCourse(courseId);
	}
}

// TODO: Probar