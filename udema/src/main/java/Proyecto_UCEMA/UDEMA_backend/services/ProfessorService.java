package Proyecto_UCEMA.UDEMA_backend.services;

import java.util.List;

import Proyecto_UCEMA.UDEMA_backend.models.Professor;

public interface ProfessorService {
	List<Professor> getProfessors();

	Professor getProfessor(Long professorId);

	void addNewProfessor(Professor professor);

	void deleteProfessor(Long professorId);

	void updateProfessor(Long professorId, Professor pProfessor);

	String authenticate(String username, String password);
}
