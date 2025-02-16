package Proyecto_UCEMA.UDEMA_backend.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtilities jwtUtilities;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
	) throws ServletException, IOException {
		// Get the JWT request token
		String token = jwtUtilities.getToken(request);
		if(token != null && jwtUtilities.validateToken(token)) { // If the token exist and it's valid
			String username = jwtUtilities.extractUsername(token); // Get token username
			UserDetails user = userDetailsService.loadUserByUsername(username); // Get new object
			if(user != null) { // If the user exists
				// Create a representation of the authentication information with the username and credentials listing.
				UsernamePasswordAuthenticationToken authentication =new UsernamePasswordAuthenticationToken(
					user.getUsername(), null, user.getAuthorities()
				);
				// Adds the user to the current security context. It will allow us to recover it throughout the application.
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		// Invoke the next chain filter.
		filterChain.doFilter(request, response);
	}
}
