package Proyecto_UCEMA.UDEMA_backend.services;

import java.util.List;

import Proyecto_UCEMA.UDEMA_backend.models.Class;

public interface ClassService {
	List<Class> getClasses();

	Class getClass(Long classId);

	Class getClassByCourseIdAndClassNum(Long courseId, Integer classNum);

	void updateClass(Long classId, Class pClass);

	List<Class> getClassesInCourse(Long courseId);

	void addNewClass(Class pClass, Long courseId);

	void removeClass(Long classId);

	void removeClassByCourseIdAndClassNum(Long courseId, Integer classNumber);
}
