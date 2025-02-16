package Proyecto_UCEMA.UDEMA_backend.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtilities {
	private final String secret = "thisisasecretkeythatyoushouldntgivetoanyoneyouhearmeokgotitnowletscoverthiwspace71234732147asdbdg02314sdgffg70210asdfsg345389enough";
	private Long expiration = 3600L;
	private final Set<String> invalidTokens = new HashSet<>();  // Lista de tokens inválidos

	public String generateToken(String username, Long id, String rol) {
		return Jwts.builder()
			.setSubject(username) // Subject
			.claim("id", id) // Claims - user information in the token.
			.setIssuedAt(new Date(System.currentTimeMillis())) // Token emission date.
			.setExpiration(Date.from(Instant.now().plus(expiration, ChronoUnit.SECONDS))) // Token expiration date.
			.signWith(SignatureAlgorithm.HS256, secret) // Token signature.
			.compact(); // JWT chain creation with the established configuration.
	}

	public String getToken(HttpServletRequest httpServletRequest) {
		String barrerToken = httpServletRequest.getHeader("Authorization"); // Get the Authorization Header content.
		if(StringUtils.hasText(barrerToken) && barrerToken.startsWith("Bearer ")) { // Verify the token to be JWT.
			return barrerToken.substring(7); // Get the token chain.
		}
		return null;
	}

	public boolean validateToken(String token) {
		try {
			// Check if the token is valid by obtaining the claims
			Jws<Claims> claimsJws = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token);
			// Token expiration verification.
			return !isTokenExpired(token);
		} catch (Exception e) {
			// If the token is invalid, you should be able to perform an action.
		}
		return false;
	}

	public void invalidateToken(String token) {
		invalidTokens.add(token); // Add the token to the invalid tokens list.
	}

	// Extraction section:

	public Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}

	public Long extractId(String token) {
		return Long.parseLong((String) extractAllClaims(token).get("id"));
	}

	public Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}

	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
}
