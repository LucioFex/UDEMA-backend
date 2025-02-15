package Proyecto_UCEMA.UDEMA_backend.services;

import java.util.List;

import Proyecto_UCEMA.UDEMA_backend.models.Class;

public interface ClassService {
	List<Class> getClasses();

	Class getClass(Long classId);

	void deleteClass(Long classId); // Deprecated

	void updateClass(Long classId, Class pClass);
}
