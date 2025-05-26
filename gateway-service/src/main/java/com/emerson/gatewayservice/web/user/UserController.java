package com.emerson.gatewayservice.web.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emerson.gatewayservice.client.auth.dto.UpdateRolesRequest;
import com.emerson.gatewayservice.client.auth.dto.UpdateUsernameRequest;
import com.emerson.gatewayservice.client.userinfo.dto.UpdatePersonalInfoRequest;
import com.emerson.gatewayservice.service.user.UserFacade;
import com.emerson.gatewayservice.service.user.dto.UserDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
	
	private final UserFacade userFacade;

	public UserController(UserFacade userFacade) {
		this.userFacade = userFacade;
	}
	
	@GetMapping("/me")
	public ResponseEntity<UserDto> getLoggedInUser(JwtAuthenticationToken token) {
		String loggedInUserId = token.getToken().getSubject();
		return ResponseEntity.ok(this.userFacade.getById(loggedInUserId));
	}
	
	// ADMIN
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> get(@PathVariable String id, JwtAuthenticationToken token) {
		System.out.println("userId: " + token.getToken().getSubject());
		System.out.println("roles: " + token.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList() + "\n");
		
		return ResponseEntity.ok(this.userFacade.getById(id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}/username")
	public ResponseEntity<UserDto> updateUsername(@PathVariable String id, @RequestBody @Valid UpdateUsernameRequest usernameRequest) {
		return ResponseEntity.ok(this.userFacade.updateUsername(id, usernameRequest));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}/roles")
	public ResponseEntity<UserDto> updateRoles(@PathVariable String id, @RequestBody @Valid UpdateRolesRequest rolesRequest) {
		return ResponseEntity.ok(this.userFacade.updateRoles(id, rolesRequest));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}/personal-info")
	public ResponseEntity<UserDto> updatePersonalInfo(@PathVariable String id, @RequestBody @Valid UpdatePersonalInfoRequest personalInfoRequest) {
		return ResponseEntity.ok(this.userFacade.updatePersonalInfo(id, personalInfoRequest));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) {
		this.userFacade.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	// TESTER
	@PreAuthorize("hasRole('TESTER')")
	@GetMapping("/tester")
	public ResponseEntity<Object> tester(JwtAuthenticationToken token) {
		System.out.println("token.getCredentials(): " + token.getCredentials());
		System.out.println("token.getName(): " + token.getName());
		System.out.println("token.getDetails(): " + token.getDetails());
		System.out.println("token.getPrincipal(): " + token.getPrincipal());
		
		return ResponseEntity.noContent().build();
	}

}
