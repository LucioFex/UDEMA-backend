package Proyecto_UCEMA.UDEMA_backend.services;

import java.util.List;
import java.util.Optional;

import Proyecto_UCEMA.UDEMA_backend.models.Student;

public interface StudentService {
	List<Student> getStudents();
	Optional<Student> getStudent(Long studentId);
	void addNewStudent(Student student);
	void deleteStudent(Long studentId);
	void updateStudent(Long studentId, Student pStudent);
	void changePasswordStudent(Long studentId, String password);
}
