package Proyecto_UCEMA.UDEMA_backend.dto;

import java.util.List;

public class CourseDTO {
	private Long id;
	private String name;
	private String description;
	private List<Long> studentIds;
	private List<Long> classIds;
	private Long professorId;

	public CourseDTO() {}

	public CourseDTO(Long id, String name, String description, List<Long> studentIds, List<Long> classIds, Long professorId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.studentIds = studentIds;
		this.classIds = classIds;
		this.professorId = professorId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Long> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(List<Long> studentIds) {
		this.studentIds = studentIds;
	}

	public List<Long> getClassIds() {
		return classIds;
	}

	public void setClassIds(List<Long> classIds) {
		this.classIds = classIds;
	}

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}
}
