package Proyecto_UCEMA.UDEMA_backend.models;

public class User {
    private Long id;
	private String name;
	private String surname;
	private Integer age;
	private String email;
	private String password;

	public User() {
	}

	public User(Long id, String name, String surname, Integer age, String email, String password) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.password = password;
	}
	public User(String name, String surname, Integer age, String email, String password) {
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
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
		return this.age;
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
