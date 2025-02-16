package Proyecto_UCEMA.UDEMA_backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Proyecto_UCEMA.UDEMA_backend.repositories.PersonRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private PersonRepository personRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return personRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
	}
}
