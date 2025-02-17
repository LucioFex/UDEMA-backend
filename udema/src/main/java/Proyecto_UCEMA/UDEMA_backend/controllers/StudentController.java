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

import Proyecto_UCEMA.UDEMA_backend.models.Student;
import Proyecto_UCEMA.UDEMA_backend.services.StudentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping(path = "api/students")
public class StudentController {
	private final StudentServiceImpl studentService;

	public StudentController(StudentServiceImpl studentService) {
		this.studentService = studentService;
	}

	@Operation(summary = "Get students", description = "Get all the students")
	@GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

	@Operation(summary = "Get specific student", description = "Get specific student by ID")
	@GetMapping("{studentId}")
	public Student getStudent(@PathVariable("studentId") Long studentId) {
		return studentService.getStudent(studentId);
	}

	@Operation(summary = "Register new student", description = "Register a new student")
	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}

	@Operation(summary = "Delete student", description = "Delete an specific student by ID")
	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
	}

	@Operation(summary = "Update student", description = "Update an specific student by ID")
	@PutMapping("{studentId}")
	public void updateStudent(
			@PathVariable("studentId") Long studentId,
			@RequestBody Student student) {
		studentService.updateStudent(studentId, student);
	}
}
