package Proyecto_UCEMA.UDEMA_backend.services;

import java.time.LocalDate;
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
import Proyecto_UCEMA.UDEMA_backend.models.Student;
import Proyecto_UCEMA.UDEMA_backend.repositories.PersonRepository;
import Proyecto_UCEMA.UDEMA_backend.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService{
	private final PersonRepository personRepository;
	private final StudentRepository studentRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtilities jwtUtilities;

	public StudentServiceImpl(PersonRepository personRepository, StudentRepository studentRepository) {
		this.personRepository = personRepository;
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudent(Long studentId) {
		return studentRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student not found"));
	}

	@Override
	@Transactional
	public void addNewStudent(Student student) {
		if (student.getSubmissionDate() == null || student.getCareer() == null) {
			throw new ResponseStatusException(
				HttpStatus.UNPROCESSABLE_ENTITY,
				"You're missing the submission date to add a student"
			);
		}
		if (student.getUsername() == null) student.setUsername(null); // It will set a default username. (TODO: Esto sería automático si utilizara un DTO para esta clase, ya que ejecutaría la revisión del constructor. Por cuestiones de tiempo no lo implementaré)
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		studentRepository.save(student);
	}

	@Override
	@Transactional
	public void deleteStudent(Long studentId) {
		getStudent(studentId); // Student existence validation.
		studentRepository.deleteById(studentId);
	}

	@Override
	@Transactional
	public void updateStudent(Long studentId, Student pStudent) { // TODO: Allow password modification.
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException( "Student with id " + studentId + " doesn\'t exist" ));

		if (pStudent.getName() != null && !Objects.equals(student.getName(), pStudent.getName())) {
			student.setName(pStudent.getName());
		}
		if (pStudent.getSubmissionDate() != null && !Objects.equals(student.getSubmissionDate(), pStudent.getSubmissionDate())) {
			student.setSubmissionDate(pStudent.getSubmissionDate());
		}
		if (pStudent.getCareer() != null && !Objects.equals(student.getCareer(), pStudent.getCareer())) {
			student.setCareer(pStudent.getCareer());
			student.setSubmissionDate(LocalDate.now()); // The submission date is updated due to career change
		}
		if (pStudent.getEmail() != null && pStudent.getEmail().length() > 0 && !Objects.equals(student.getEmail(), pStudent.getEmail())) {
			Optional<Person> personOptional = personRepository.findPersonByEmail(pStudent.getEmail());

			if (personOptional.isPresent()) {
				throw new EntityNotFoundException("Email taken");
			}
			student.setEmail(pStudent.getEmail());
		}
	}

	@Override
	public String authenticate(String username, String password) {
		Person user = personRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Person not found"));
		Student student = getStudent(user.getId());

		if (student == null || !passwordEncoder.matches(password, student.getPassword())) return null;

		// Token generation in return.
		String token = jwtUtilities.generateToken(student.getUsername(), student.getId(), student.getRole());
		return token;
	}
}
