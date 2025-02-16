package Proyecto_UCEMA.UDEMA_backend.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;

@Entity
@DiscriminatorValue("student")
public class Student extends Person {
	private LocalDate submissionDate;
	private String career;
	@Transient
	private Integer year;

	@ManyToMany(
		fetch = FetchType.LAZY,
		cascade = { CascadeType.PERSIST, CascadeType.MERGE }
	)
	@JoinTable(
		name = "student_course",
		joinColumns = @JoinColumn(name = "student_id"),
		inverseJoinColumns = @JoinColumn(name = "course_id")
	)
	private List<Course> courses;

	public Student() {}

	public Student(String name, String surname, String email, LocalDate dateOfBirth, String password, LocalDate submissionDate, String career) {
		super(name, surname, email, dateOfBirth, password);
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

	@Override
	public String getRole() {
		return "ROLE_STUDENT";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
		return authorities;
	}
}
