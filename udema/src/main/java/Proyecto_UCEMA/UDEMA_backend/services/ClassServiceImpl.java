package Proyecto_UCEMA.UDEMA_backend.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Proyecto_UCEMA.UDEMA_backend.models.Class;
import Proyecto_UCEMA.UDEMA_backend.models.Course;
import Proyecto_UCEMA.UDEMA_backend.repositories.ClassRepository;
import Proyecto_UCEMA.UDEMA_backend.repositories.CourseRepository;
import jakarta.transaction.Transactional;

@Service
public class ClassServiceImpl implements ClassService {
	private final ClassRepository classRepository;
	private final CourseRepository courseRepository;

	public ClassServiceImpl(ClassRepository classRepository, CourseRepository courseRepository) {
		this.classRepository = classRepository;
		this.courseRepository = courseRepository;
	}

	@Override
	public List<Class> getClasses() {
		return classRepository.findAll();
	}

	@Override
	public Class getClass(Long classId) {
		return classRepository.findById(classId).orElseThrow(() -> new IllegalArgumentException("Class not found"));
	}

	@Override
	public Class getClassByCourseIdAndClassNum(Long courseId, Integer classNum) {
		return classRepository.findClassByCourseIdAndNumber(courseId, classNum).orElseThrow(() -> new IllegalArgumentException("Class not found"));
	}

	@Override
	public List<Class> getClassesInCourse(Long courseId) {
		return classRepository.findAllByCourseId(courseId);
	}

	@Override
	@Transactional
	public void updateClass(Long classId, Class pClass) {
		Class uClass = classRepository.findById(classId)
			.orElseThrow(() -> new IllegalArgumentException("Class with id " + classId + " doesn\'t exist"));

		// Class number update if distinct from 0 and the previous value.
		if (pClass.getNumber() != 0 && !Objects.equals(pClass.getNumber(), uClass.getNumber())) {
			uClass.setNumber(pClass.getNumber());
		}
		// Class classroom update if not null and the previous value.
		if (pClass.getClassroom() != null && uClass.getClassroom().length() > 0 && !Objects.equals(pClass.getClassroom(), uClass.getClassroom())) {
			uClass.setClassroom(pClass.getClassroom());
		}
		// Class date update if not null and the previous value.
		if (pClass.getDate() != null && !Objects.equals(pClass.getDate(), uClass.getDate())) {
			uClass.setDate(pClass.getDate());
		}
		classRepository.save(uClass);
	}

	@Override
	@Transactional
	public void addNewClass(Class pClass, Long courseId) {
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Course not found"));
		Optional<Class> topClass = classRepository.findTopByCourseIdOrderByNumberDesc(courseId);

		Integer lastClassNumber = 0;
		if (topClass.isPresent()) lastClassNumber = topClass.get().getNumber();

		// Class uClass = new Class(lastClassNumber + 1, );
		Class uClass = new Class(lastClassNumber + 1, pClass.getClassroom(), pClass.getDate(), course);

		// course.addSClass(uClass);
		classRepository.save(uClass);
		courseRepository.save(course);
	}

	@Override
	@Transactional
	public void removeClass(Long classId) {
		classRepository.deleteById(classId);
	}

	@Override
	@Transactional
	public void removeClassByCourseIdAndClassNum(Long courseId, Integer classNum) {
		Class uClass = classRepository.findClassByCourseIdAndNumber(courseId, classNum)
			.orElseThrow(() -> new IllegalArgumentException("Class not found"));
		removeClass(uClass.getId());
	}
}
