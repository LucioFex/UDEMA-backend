package Proyecto_UCEMA.UDEMA_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Proyecto_UCEMA.UDEMA_backend.models.Professor;

@Repository
public interface ProfessorRepository
		extends JpaRepository<Professor, Long> {
	Optional<Professor> findProfessorByEmail(String email);
}