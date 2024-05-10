package Proyecto_UCEMA.UDEMA_backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import Proyecto_UCEMA.UDEMA_backend.models.Professor;
import Proyecto_UCEMA.UDEMA_backend.services.ProfessorService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "api/professors")
public class ProfessorController {
	private final ProfessorService professorService;

	public ProfessorController(ProfessorService professorService) {
		this.professorService = professorService;
	}

	@GetMapping
	public List<Professor> getProfessors() {
		return professorService.getProfessors();
	}

	@PostMapping
	public void registerNewProfessor(@RequestBody Professor professor) {
		professorService.addNewProfessor(professor);
	}

	@DeleteMapping(path = "{professorId}")
	public void deleteProfessor(@PathVariable("professorId") Long professorId) {
		professorService.deleteProfessor(professorId);
	}

	@PutMapping("{professorId}")
	public void updateProfessor(
			@PathVariable("professorId") Long professorId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
		professorService.updateProfessor(professorId, name, email);
	}
}
