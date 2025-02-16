package Proyecto_UCEMA.UDEMA_backend.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("professor")
public class Professor extends Person {
	private LocalDate submissionDate;

	@OneToOne(mappedBy = "professor")
	private Course course;

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

	@Override
	public String getRole() {
		return "ROLE_PROFESSOR";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_PROFESSOR"));
		return authorities;
	}
}
