package Proyecto_UCEMA.UDEMA_backend.models;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Transient;

@Entity
@PrimaryKeyJoinColumn(name = "personId")
public class Student extends Person {
	private LocalDate submissionDate;
	private String career;
	@Transient
	private Integer year;

	public Student() {}

	public Student(String name, String surname, String email, LocalDate dob, String password, LocalDate submissionDate, String career) {
		super(name, surname, email, dob, password);
		this.submissionDate = submissionDate;
		this.career = career;
	}

	public LocalDate getSubmissionDate() {
		return this.submissionDate;
	}

	public void setSubmissionDate(LocalDate submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getCareer() {
		return this.career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public Integer getYear() {
		return Period.between(this.submissionDate, LocalDate.now()).getYears();
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
