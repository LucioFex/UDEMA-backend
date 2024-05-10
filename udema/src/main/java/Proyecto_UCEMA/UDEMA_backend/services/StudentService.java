package Proyecto_UCEMA.UDEMA_backend.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import Proyecto_UCEMA.UDEMA_backend.models.Student;
import Proyecto_UCEMA.UDEMA_backend.models.Person;
import Proyecto_UCEMA.UDEMA_backend.repositories.StudentRepository;
import jakarta.transaction.Transactional;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Person> personOptional = studentRepository
			.findPersonByEmail(student.getEmail());
		
		if (personOptional.isPresent()) {
			throw new IllegalStateException("Email taken");
		}
		else if (student.getCareer() == null) {
			throw new ResponseStatusException(
				HttpStatus.UNPROCESSABLE_ENTITY,
				"The 'career' field is required to add a student"
			);
		}
		else if (student.getSubmissionDate() == null) {
			throw new ResponseStatusException(
				HttpStatus.UNPROCESSABLE_ENTITY,
				"The submission date is required to add a student"
			);
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if (!exists) {
			throw new IllegalStateException(
				"Student with id " + studentId + " doesn\'t exist"
			);
		}
		studentRepository.deleteById(studentId);
	}

	@Transactional
	public void updateStudent(Long studentId, String name, String email, String career) {
		Student student = studentRepository.findById(studentId)
			.orElseThrow(() -> new IllegalStateException(
				"Student with id " + studentId + " doesn\'t exist"
			));
		
		if (name != null && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		if (career != null && !Objects.equals(student.getCareer(), career)) {
			student.setCareer(career);
			student.setSubmissionDate(LocalDate.now()); // The submission date is updated due to career change
		}
		if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Person> personOptional = studentRepository
				.findPersonByEmail(email);

			if (personOptional.isPresent()) {
				throw new IllegalStateException("Email taken");
			}
			student.setEmail(email);
		}
	}

	@Transactional
	public void changePasswordStudent(Long studentId, String password) {
		Student student = studentRepository.findById(studentId)
			.orElseThrow(() -> new IllegalStateException(
				"Student with id " + studentId + " doesn\'t exist"
			));
		
		// TODO: Add security
		if (password != null && !Objects.equals(student.getPassword(), password)) {
			student.setPassword(password);
		}
	}
}
