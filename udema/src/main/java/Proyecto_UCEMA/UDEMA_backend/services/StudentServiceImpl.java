package Proyecto_UCEMA.UDEMA_backend.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import Proyecto_UCEMA.UDEMA_backend.models.Person;
import Proyecto_UCEMA.UDEMA_backend.models.Student;
import Proyecto_UCEMA.UDEMA_backend.repositories.StudentRepository;
import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService{

	private final StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public Optional<Student> getStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if (!exists) {
			throw new IllegalStateException(
				"Student with id " + studentId + " doesn\'t exist"
			);
		}
		return studentRepository.findById(studentId);
	}


	public void addNewStudent(Student student) {
		if (student.getSubmissionDate() == null || student.getCareer() == null) {
			throw new ResponseStatusException(
				HttpStatus.UNPROCESSABLE_ENTITY,
				"You're missing data to add an student"
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
	public void updateStudent(Long studentId, Student pStudent) {
		Student student = studentRepository.findById(studentId)
			.orElseThrow(() -> new IllegalStateException(
				"Student with id " + studentId + " doesn\'t exist"
			));

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
			Optional<Person> personOptional = studentRepository
				.findPersonByEmail(pStudent.getEmail());

			if (personOptional.isPresent()) {
				throw new IllegalStateException("Email taken");
			}
			student.setEmail(pStudent.getEmail());
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
