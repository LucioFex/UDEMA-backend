package Proyecto_UCEMA.UDEMA_backend.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import Proyecto_UCEMA.UDEMA_backend.config.JwtUtilities;
import Proyecto_UCEMA.UDEMA_backend.models.Person;
import Proyecto_UCEMA.UDEMA_backend.models.Professor;
import Proyecto_UCEMA.UDEMA_backend.repositories.PersonRepository;
import Proyecto_UCEMA.UDEMA_backend.repositories.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProfessorServiceImpl implements ProfessorService {
	private final PersonRepository personRepository;
	private final ProfessorRepository professorRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtilities jwtUtilities;

	public ProfessorServiceImpl(PersonRepository personRepository, ProfessorRepository professorRepository) {
		this.personRepository = personRepository;
		this.professorRepository = professorRepository;
	}

	@Override
	public List<Professor> getProfessors() {
		return professorRepository.findAll();
	}

	@Override
	public Professor getProfessor(Long professorId) {
		return professorRepository.findById(professorId).orElseThrow(() -> new IllegalArgumentException("Professor not found"));
	}

	@Override
	public void addNewProfessor(Professor professor) {
		if (professor.getSubmissionDate() == null) {
			throw new ResponseStatusException(
				HttpStatus.UNPROCESSABLE_ENTITY,
				"You're missing the submission date to add a professor"
			);
		}
		professor.setPassword(passwordEncoder.encode(professor.getPassword()));
		professorRepository.save(professor);
	}

	@Override
	@Transactional
	public void deleteProfessor(Long professorId) {
		getProfessor(professorId); // Professor existence validation.
		professorRepository.deleteById(professorId);
	}

	@Override
	@Transactional
	public void updateProfessor(Long professorId, Professor pProfessor) {
		Professor professor = professorRepository.findById(professorId)
			.orElseThrow(() -> new IllegalStateException(
				"Professor with id " + professorId + " doesn\'t exist"
			));

		if (pProfessor.getName() != null && !Objects.equals(professor.getName(), pProfessor.getName())) {
			professor.setName(pProfessor.getName());
		}
		if (pProfessor.getSubmissionDate() != null && !Objects.equals(professor.getSubmissionDate(), pProfessor.getSubmissionDate())) {
			professor.setSubmissionDate(pProfessor.getSubmissionDate());
		}
		if (pProfessor.getEmail() != null && pProfessor.getEmail().length() > 0 && !Objects.equals(professor.getEmail(), pProfessor.getEmail())) {
			Optional<Person> personOptional = personRepository.findPersonByEmail(pProfessor.getEmail());

			if (personOptional.isPresent()) {
				throw new IllegalStateException("Email taken");
			}
			professor.setEmail(pProfessor.getEmail());
		}
	}

	@Override
	public String authenticate(String username, String password) {
		Person user = personRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Person not found"));
		Professor professor = getProfessor(user.getId());

		if (professor == null || !passwordEncoder.matches(password, professor.getPassword())) return null;

		// Token generation in return.
		String token = jwtUtilities.generateToken(professor.getUsername(), professor.getId(), professor.getRole());
		return token;
	}
}
