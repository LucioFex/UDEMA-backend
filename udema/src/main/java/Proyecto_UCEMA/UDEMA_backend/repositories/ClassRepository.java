package Proyecto_UCEMA.UDEMA_backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Proyecto_UCEMA.UDEMA_backend.models.Class;

@Repository
public interface ClassRepository
		extends JpaRepository<Class, Long> {
	/* @Query("SELECT c FROM class c WHERE c.course_id = :courseId")
	List<Class> findClassessByCourseId(@Param("courseId") Long courseId); */
	List<Class> findAllByCourseId(Long courseId);

	@Query("SELECT c FROM Class c WHERE c.course.id = :courseId AND c.number = :classNumber")
	Optional<Class> findClassByCourseIdAndNumber(@Param("courseId") Long courseId, @Param("classNumber") Integer classNumber);

	@Query("SELECT c FROM Class c WHERE c.course.id = :courseId ORDER BY c.number DESC LIMIT 1")
	Optional<Class> findTopByCourseIdOrderByNumberDesc(@Param("courseId") Long courseId);
}
