package com.emerson.authservice.web.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emerson.authservice.service.auth.AuthServiceFacade;
import com.emerson.authservice.service.auth.dto.LoginRequest;
import com.emerson.authservice.service.auth.dto.TokenDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/internal/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	
	private final AuthServiceFacade authServiceFacade;
	
	public AuthController(AuthServiceFacade authServiceFacade) {
		this.authServiceFacade = authServiceFacade;
	}

	@PostMapping("/login")
	public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginRequest request) {
		return ResponseEntity.ok(this.authServiceFacade.loginWithUsernameAndPassword(request.username(), request.password()));
	}
	
}
