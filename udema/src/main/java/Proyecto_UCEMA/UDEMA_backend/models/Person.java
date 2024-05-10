package Proyecto_UCEMA.UDEMA_backend.models;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
	@Id
	@SequenceGenerator(
		name = "person_sequence",
		sequenceName = "person_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "person_sequence"
	)
	private Long id;
	private String name;
	private String surname;
	private String email;
	private String password;
	private LocalDate dob;
	@Transient
	private Integer age;

	public Person() {
	}

	public Person(Long id, String name, String surname, String email, LocalDate dob, String password) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.dob = dob;
		this.password = password;
	}
	public Person(String name, String surname, String email, LocalDate dob, String password) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.dob = dob;
		this.password = password;
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

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getAge() {
		return Period.between(this.dob, LocalDate.now()).getYears();
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDob() {
		return this.dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", name='" + getName() + "'" +
			", surname='" + getSurname() + "'" +
			", age='" + getAge() + "'" +
			", email='" + getEmail() + "'" +
			", password='" + getPassword() + "'" +
			"}";
	}
}
