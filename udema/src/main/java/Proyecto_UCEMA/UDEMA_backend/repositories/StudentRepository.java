package Proyecto_UCEMA.UDEMA_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Proyecto_UCEMA.UDEMA_backend.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	// Optional<Student> findStudentById(Long studentId); // No es necesario especificar este ID, Spring es lo suficientemente inteligente para realizar esta definición en cualquier caso de uso desde la implementación
}
