package Proyecto_UCEMA.UDEMA_backend.services;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import Proyecto_UCEMA.UDEMA_backend.models.Class;
import Proyecto_UCEMA.UDEMA_backend.repositories.ClassRepository;

@Service
public class ClassServiceImpl implements ClassService {
	private final ClassRepository classRepository;

	public ClassServiceImpl(ClassRepository classRepository) {
		this.classRepository = classRepository;
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
	public void deleteClass(Long classId) {
		boolean exists = classRepository.existsById(classId);
		if (!exists) { throw new IllegalArgumentException("Class with id " + classId + " doesn\'t exist"); }
		classRepository.deleteById(classId);
	}

	@Override
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
}
