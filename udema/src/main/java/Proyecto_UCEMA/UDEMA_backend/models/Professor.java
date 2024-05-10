package Proyecto_UCEMA.UDEMA_backend.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "personId")
public class Professor extends Person {
	private LocalDate submissionDate;

	public Professor() {}

	public Professor(String name, String surname, String email, LocalDate dob, String password, LocalDate submissionDate) {
		super(name, surname, email, dob, password);
		this.submissionDate = submissionDate;
	}

	public LocalDate getSubmissionDate() {
		return this.submissionDate;
	}

	public void setSubmissionDate(LocalDate submissionDate) {
		this.submissionDate = submissionDate;
	}

}
