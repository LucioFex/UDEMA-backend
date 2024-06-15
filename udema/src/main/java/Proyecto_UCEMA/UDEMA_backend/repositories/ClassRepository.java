package Proyecto_UCEMA.UDEMA_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Proyecto_UCEMA.UDEMA_backend.models.Class;

@Repository
public interface ClassRepository
		extends JpaRepository<Class, Long> {
}
