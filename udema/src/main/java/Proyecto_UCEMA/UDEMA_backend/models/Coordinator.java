package Proyecto_UCEMA.UDEMA_backend.models;
import java.time.LocalDate;
import java.util.Objects;

public class Coordinator extends Person {

	public Coordinator() {
	}
	
	public Coordinator(String name, String surname, String email, LocalDate dateOfBirth, String password) {
		super(name, surname, email, dateOfBirth, password);
	}
}
