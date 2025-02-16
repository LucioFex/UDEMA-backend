package Proyecto_UCEMA.UDEMA_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import Proyecto_UCEMA.UDEMA_backend.config.JwtUtilities;
import Proyecto_UCEMA.UDEMA_backend.dto.LoginDTO;
import Proyecto_UCEMA.UDEMA_backend.dto.ResponseLoginDTO;
import Proyecto_UCEMA.UDEMA_backend.services.ProfessorService;
import Proyecto_UCEMA.UDEMA_backend.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping(value = "/api/auth")
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AuthController {
	@Autowired
	private StudentService studentService;

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private JwtUtilities jwtUtilities;

	// Students section

	@Operation(summary = "Student login", description = "Login as a student")
	@PostMapping(path = "student/login")
	public ResponseLoginDTO authenticateStudent(@RequestBody LoginDTO loginDTO) {
		return new ResponseLoginDTO(this.studentService.authenticate(loginDTO.getUsername(), loginDTO.getPassword()));
	}

	@Operation(summary = "Student logout", description = "Logout as a student")
	@PostMapping("student/logout")
	public void logoutStudent(@RequestHeader("Authorization") String token) {
		if (token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		jwtUtilities.invalidateToken(token);
	}

	// Professors section

	@Operation(summary = "Professor login", description = "Login as a professor")
	@PostMapping(path = "professor/login")
	public ResponseLoginDTO authenticateProfessor(@RequestBody LoginDTO loginDTO) {
		return new ResponseLoginDTO(this.professorService.authenticate(loginDTO.getUsername(), loginDTO.getPassword()));
	}

	@Operation(summary = "Professor logout", description = "Logout as a professor")
	@PostMapping("professor/logout")
	public void logoutProfessor(@RequestHeader("Authorization") String token) {
		if (token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		jwtUtilities.invalidateToken(token);
	}
}
