package Proyecto_UCEMA.UDEMA_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UdemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemaApplication.class, args);
		System.setProperty("spring.jpa.hibernate.ddl-auto", "validate");
	}
}
