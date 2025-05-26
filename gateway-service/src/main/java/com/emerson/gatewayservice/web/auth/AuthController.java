package com.emerson.gatewayservice.web.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emerson.gatewayservice.client.auth.dto.LoginRequest;
import com.emerson.gatewayservice.client.auth.dto.TokenDto;
import com.emerson.gatewayservice.service.auth.AuthFacade;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	
	private final AuthFacade authFacade;
	

	public AuthController(AuthFacade authFacade) {
		this.authFacade = authFacade;
	}

	@PostMapping("/login")
	public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginRequest request) {
		return ResponseEntity.ok(this.authFacade.login(request));
	}
	
}
