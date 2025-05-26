package com.emerson.authservice.web.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emerson.authservice.domain.user.User;
import com.emerson.authservice.service.user.create.CreateUserServiceFacade;
import com.emerson.authservice.service.user.create.dto.CreateUserRequest;
import com.emerson.authservice.service.user.delete.DeleteUserServiceFacade;
import com.emerson.authservice.service.user.read.FindUserServiceFacade;
import com.emerson.authservice.service.user.update.UpdateUserServiceFacade;
import com.emerson.authservice.service.user.update.dto.UpdatePasswordRequest;
import com.emerson.authservice.service.user.update.dto.UpdateRolesRequest;
import com.emerson.authservice.service.user.update.dto.UpdateUsernameRequest;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/internal/v1/user")
@CrossOrigin(origins = "*")
@Slf4j
public class UserController {
	
	private final FindUserServiceFacade findUserService;
	private final CreateUserServiceFacade createUserService;
	private final UpdateUserServiceFacade updateUserService;
	private final DeleteUserServiceFacade deleteUserService;
	
	public UserController(
			FindUserServiceFacade findUserService,
			CreateUserServiceFacade createUserService,
			UpdateUserServiceFacade updateUserService,
			DeleteUserServiceFacade deleteUserService) 
	{
		this.findUserService = findUserService;
		this.createUserService = createUserService;
		this.updateUserService = updateUserService;
		this.deleteUserService = deleteUserService;
	}

	@PreAuthorize("hasAuthority('users:create')")
	@PostMapping
	public ResponseEntity<User> register(@RequestBody @Valid CreateUserRequest userDto) {
		return new ResponseEntity<>(this.createUserService.create(userDto), HttpStatus.CREATED);
	}

	@PreAuthorize("hasAuthority('users:get')")
	@GetMapping("/{id}")
	public ResponseEntity<User> get(@PathVariable String id, @AuthenticationPrincipal Jwt jwt, Authentication authentication) {
		System.out.println("[auth-service] token: " + jwt.getTokenValue());
		System.out.println("[auth-service] token authorities: " + authentication.getAuthorities());
		System.out.println("[auth-service] token realm_access: " + jwt.getClaimAsStringList("realm_access"));
		System.out.println("[auth-service] token claims: " + jwt.getClaims());
		
		return ResponseEntity.ok(this.findUserService.getById(id));
	}

	@PreAuthorize("hasAuthority('users:get')")
	@GetMapping("/{id}/roles")
	public ResponseEntity<List<String>> getRolesById(@PathVariable String id) {
		log.info("Obtendo as roles do usuário: " + id + "...");
		
		return ResponseEntity.ok(this.findUserService.getRolesById(id));
	}

	@PreAuthorize("hasAuthority('users:update')")
	@PutMapping("/{id}/username")
	public ResponseEntity<Object> updateUsername(@PathVariable String id, @RequestBody @Valid UpdateUsernameRequest usernameRequest) {
		this.updateUserService.updateUsername(id, usernameRequest.username());
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAuthority('users:update')")
	@PutMapping("/{id}/password")
	public ResponseEntity<Object> updatePassword(@PathVariable String id, @RequestBody @Valid UpdatePasswordRequest passwordRequest) {
		this.updateUserService.updatePassword(
				id, 
				passwordRequest.currentPassword(),
				passwordRequest.newPassword(), 
				passwordRequest.newPasswordRepeat()
		);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAuthority('users:update')")
	@PutMapping("/{id}/roles")
	public ResponseEntity<Object> updateRoles(@PathVariable String id, @RequestBody @Valid UpdateRolesRequest rolesRequest) {
		this.updateUserService.updateRoles(id, rolesRequest.roles());
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAuthority('users:delete')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) {
		this.deleteUserService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	// TESTER
	@PreAuthorize("hasRole('TESTER')")
	@GetMapping("/tester")
	public ResponseEntity<Object> tester(JwtAuthenticationToken token) {
		System.out.println("token.getCredentials(): " + token.getCredentials());
		System.out.println("token.getName(): " + token.getName());
		System.out.println("token.getDetails(): " + token.getDetails());
		System.out.println("token.getPrincipal().getId(): " + token.getPrincipal());
		
		return ResponseEntity.noContent().build();
	}

}
