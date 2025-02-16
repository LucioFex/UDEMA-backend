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

import Proyecto_UCEMA.UDEMA_backend.models.Professor;
import Proyecto_UCEMA.UDEMA_backend.services.ProfessorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping(path = "api/professors")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfessorController {
	private final ProfessorServiceImpl professorService;

	public ProfessorController(ProfessorServiceImpl professorService) {
		this.professorService = professorService;
	}

	@Operation(summary = "Get professor", description = "Get all professors")
	@GetMapping
	public List<Professor> getProfessors() {
		return professorService.getProfessors();
	}

	@Operation(summary = "Get specific professor", description = "Get specific professor by ID")
	@GetMapping("{professorId}")
	public Professor getProfessor(@PathVariable("professorId") Long professorId) {
		return professorService.getProfessor(professorId);
	}

	@Operation(summary = "Register new professor", description = "Add a new professor")
	@PostMapping
	public void registerNewProfessor(@RequestBody Professor professor) {
		professorService.addNewProfessor(professor);
	}

	@Operation(summary = "Delete professor", description = "Delete a professor by it's ID")
	@DeleteMapping(path = "{professorId}")
	public void deleteProfessor(@PathVariable("professorId") Long professorId) {
		professorService.deleteProfessor(professorId);
	}

	@Operation(summary = "Update professor", description = "Update a professor by it's ID")
	@PutMapping("{professorId}")
	public void updateProfessor(
			@PathVariable("professorId") Long professorId,
			@RequestBody Professor professor) {
		professorService.updateProfessor(professorId, professor);
	}
}
