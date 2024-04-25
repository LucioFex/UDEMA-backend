package Proyecto_UCEMA.UDEMA_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Proyecto_UCEMA.UDEMA_backend.models.Person;

@Repository
public interface PersonRepository
    extends JpaRepository<Person, Long> {

}
