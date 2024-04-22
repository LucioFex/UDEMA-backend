package Proyecto_UCEMA.UDEMA_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import Proyecto_UCEMA.UDEMA_backend.models.User;

@Service
public class UserService {
	public List<User> getUsers() {
		return List.of(
			new User(
				1L,
				"Severus",
				"Snape",
				69,
				"severus.snape@gmail.com",
				"testing123"
			)
		);
	}
}
