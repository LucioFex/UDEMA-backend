package Proyecto_UCEMA.UDEMA_backend.controllers;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Proyecto_UCEMA.UDEMA_backend.models.Class;
import Proyecto_UCEMA.UDEMA_backend.services.ClassServiceImpl;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping(path = "api/classes")
@CrossOrigin(origins = "http://localhost:4200")
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

	@Operation(summary = "Delete class", description = "Delete an specific class by its ID")
	@DeleteMapping(path = "{classId}")
	public void deleteClass(@PathVariable("classId") Long classId) {
		classService.deleteClass(classId);
	}

	@Operation(summary = "Update class", description = "Update an specific class by its ID")
	@PutMapping("{classId}")
	public void updateClass(@PathVariable("classId") Long classId, @RequestBody Class pClass) {
		classService.updateClass(classId, pClass);
	}
}
