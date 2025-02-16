package Proyecto_UCEMA.UDEMA_backend.controllers;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto_UCEMA.UDEMA_backend.models.Class;
import Proyecto_UCEMA.UDEMA_backend.services.ClassServiceImpl;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping(path = "api/classes")
public class ClassController {
	private final ClassServiceImpl classService;

	public ClassController(ClassServiceImpl classService) {
		this.classService = classService;
	}

	@Operation(summary = "Get all classes", description = "Get all the classes from all the courses")
	@GetMapping
	public List<Class> getClasses() {
		return classService.getClasses();
	}

	@Operation(summary = "Get class by id", description = "Get class by its unique identifier")
	@GetMapping("{classId}")
	public Class getClass(@PathVariable("classId") Long classId) {
		return classService.getClass(classId);
	}

	@Operation(summary = "Get class by it\\'s course ID and class number", description = "Get class by it\\'s course ID and class number")
	@GetMapping("courses/{courseId}/classnum/{classNum}")
	public Class getClassByCourseIdAndClassNum(@PathVariable("courseId") Long courseId, @PathVariable("classNum") Integer classNum) {
		return classService.getClassByCourseIdAndClassNum(courseId, classNum);
	}

	@Operation(summary = "Get course classes", description = "Get all the classes in a course by it's course ID")
	@GetMapping("courses/{courseId}")
	public List<Class> getClassesByCourseId(@PathVariable("courseId") Long courseId) {
		return classService.getClassesInCourse(courseId);
	}

	@Operation(summary = "Add new class to course by id", description = "Add a new class to an existing course by \"CourseID\"")
	@PostMapping("courses/{courseId}")
	public void addNewClass(@PathVariable("courseId") Long courseId, @RequestBody Class pClass) {
		classService.addNewClass(pClass, courseId);
	}

	@Operation(summary = "Update class", description = "Update the number, classroom and date of a specific class by its ID")
	@PutMapping("{classId}")
	public void updateClass(@PathVariable("classId") Long classId, @RequestBody Class pClass) {
		classService.updateClass(classId, pClass);
	}

	@Operation(summary = "Remove class by it\'s course ID and class number", description = "Remove class by it\\'s course ID and class number")
	@DeleteMapping("{classId}")
	public void removeClass(@PathVariable("classId") Long classId) {
		classService.removeClass(classId);
	}

	@Operation(summary = "Remove class by it\'s course ID and class number", description = "Remove class by it\\'s course ID and class number")
	@DeleteMapping("courses/{courseId}/classnum/{classNum}")
	public void removeClassByCourseIdAndClassNum(@PathVariable("courseId") Long courseId, @PathVariable("classNum") Integer classNum) {
		classService.removeClassByCourseIdAndClassNum(courseId, classNum);
	}
}
