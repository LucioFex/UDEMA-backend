package Proyecto_UCEMA.UDEMA_backend.controllers;


import java.util.List;
import Proyecto_UCEMA.UDEMA_backend.services.ClassServiceImpl;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.*;
import Proyecto_UCEMA.UDEMA_backend.models.Class;


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
