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

	public List<Class> getClasses() {
		return classRepository.findAll();
	}

	public void deleteClass(Long classId) {
		boolean exists = classRepository.existsById(classId);
		if (!exists) {
			throw new IllegalStateException(
				"Class with id " + classId + " doesn\'t exist"
			);
		}
		classRepository.deleteById(classId);
	}

	public void updateClass(Long classId, Class pClass) {
		Class uClass = classRepository.findById(classId)
			.orElseThrow(() -> new IllegalStateException(
				"Class with id " + classId + " doesn\'t exist"
			));
		
		if (uClass.getNumber() != 0 && !Objects.equals(uClass.getNumber(), uClass.getNumber())) {
			uClass.setNumber(pClass.getNumber());
		}
		if (uClass.getClassroom() != null && uClass.getClassroom().length() > 0 && !Objects.equals(uClass.getClassroom(), uClass.getClassroom())) {
			uClass.setClassroom(pClass.getClassroom());
		}
		if (uClass.getDate() != null && !Objects.equals(uClass.getDate(), uClass.getDate())) {
			uClass.setDate(pClass.getDate());
		}
	}

}
