package Proyecto_UCEMA.UDEMA_backend.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import Proyecto_UCEMA.UDEMA_backend.models.Person;
import Proyecto_UCEMA.UDEMA_backend.models.Professor;
import Proyecto_UCEMA.UDEMA_backend.repositories.ProfessorRepository;
import jakarta.transaction.Transactional;

@Service
public class ProfessorService {
	private final ProfessorRepository professorRepository;

	public ProfessorService(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}

	public List<Professor> getProfessors() {
		return professorRepository.findAll();
	}

	public void addNewProfessor(Professor professor) {
		Optional<Person> personOptional = professorRepository
			.findPersonByEmail(professor.getEmail());
		
		if (personOptional.isPresent()) {
			throw new IllegalStateException("Email taken");
		}
		else if (professor.getSubmissionDate() == null) {
			throw new ResponseStatusException(
				HttpStatus.UNPROCESSABLE_ENTITY,
				"The submission date is required to add a professor"
			);
		}
		professorRepository.save(professor);
	}

	public void deleteProfessor(Long professorId) {
		boolean exists = professorRepository.existsById(professorId);
		if (!exists) {
			throw new IllegalStateException(
				"Professor with id " + professorId + " doesn\'t exist"
			);
		}
		professorRepository.deleteById(professorId);
	}

	@Transactional
	public void updateProfessor(Long professorId, String name, String email) {
		Professor professor = professorRepository.findById(professorId)
			.orElseThrow(() -> new IllegalStateException(
				"Professor with id " + professorId + " doesn\'t exist"
			));
		
		if (name != null && !Objects.equals(professor.getName(), name)) {
			professor.setName(name);
		}
		if (email != null && email.length() > 0 && !Objects.equals(professor.getEmail(), email)) {
			Optional<Person> personOptional = professorRepository
				.findPersonByEmail(email);

			if (personOptional.isPresent()) {
				throw new IllegalStateException("Email taken");
			}
			professor.setEmail(email);
		}
	}

	@Transactional
	public void changePasswordProfessor(Long professorId, String password) {
		Professor professor = professorRepository.findById(professorId)
			.orElseThrow(() -> new IllegalStateException(
				"Professor with id " + professorId + " doesn\'t exist"
			));
		
		// TODO: Add security
		if (password != null && !Objects.equals(professor.getPassword(), password)) {
			professor.setPassword(password);
		}
	}
}
