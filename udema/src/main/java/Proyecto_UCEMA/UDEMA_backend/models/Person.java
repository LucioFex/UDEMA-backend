package Proyecto_UCEMA.UDEMA_backend.models;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Specify this isn't necessary because it comes by default with JPA.
@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	private String surname;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "dateOfBirth", nullable = false)
	private LocalDate dateOfBirth;
	@Transient
	private Integer age;

	public Person() {
	}

	public Person(Long id, String name, String surname, String email, LocalDate dateOfBirth, String password) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
	}
	public Person(String name, String surname, String email, LocalDate dateOfBirth, String password) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
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
		return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
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

	public LocalDate getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
