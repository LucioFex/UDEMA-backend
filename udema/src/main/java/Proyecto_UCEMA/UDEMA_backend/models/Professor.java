package Proyecto_UCEMA.UDEMA_backend.models;

import java.time.LocalDate;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("professor")
public class Professor extends Person {
	private LocalDate submissionDate;

	public Professor() {}

	public Professor(String name, String surname, String email, LocalDate dateOfBirth, String password, LocalDate submissionDate) {
		super(name, surname, email, dateOfBirth, password);
		this.submissionDate = submissionDate;
	}

	public LocalDate getSubmissionDate() {
		return this.submissionDate;
	}

	public void setSubmissionDate(LocalDate submissionDate) {
		this.submissionDate = submissionDate;
	}

}
