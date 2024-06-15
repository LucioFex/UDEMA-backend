package Proyecto_UCEMA.UDEMA_backend.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	private String description;

	@ManyToMany
	private List<Student> students;

	@OneToMany
	private List<Class> classes;

	@OneToOne
	private Professor professor;

	public Course() {
	}

	public Course(long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Course(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
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

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void removeStudent(Student student) {
		this.students.remove(student);
	}

	public Professor getProfessor() {
		return this.professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public void addSClass(Class pClass) {
		this.classes.add(pClass);
	}

	public void removeClass(Class pClass) {
		this.classes.remove(pClass);
	}
}