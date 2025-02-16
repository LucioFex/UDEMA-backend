package Proyecto_UCEMA.UDEMA_backend.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	private String description;

	@ManyToMany
	private List<Student> students = new ArrayList<>();

	@OneToMany
	private List<Class> classes = new ArrayList<>();

	@OneToOne
	private Professor professor;

	public Course() {
	}

	public Course(Long id, String name, String description, List<Student> students, Professor professor) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.students = students;
		this.professor = professor;
	}

	public Course(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Course(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public boolean isInStudentsList(Student student) {
		return this.students.stream().anyMatch(s -> s.getId().equals(student.getId()));
	}

	public void addStudent(Student student) {
		if (!isInStudentsList(student)) { this.students.add(student); }
	}

	public void removeStudent(Student student) {
		if (isInStudentsList(student)) { this.students.remove(student); }
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public void removeProfessor() {
		this.professor = null;
	}
}