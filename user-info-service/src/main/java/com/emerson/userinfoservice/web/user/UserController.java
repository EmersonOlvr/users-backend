package com.emerson.userinfoservice.web.user;

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

import com.emerson.userinfoservice.domain.user.UserInfo;
import com.emerson.userinfoservice.service.user.create.CreateUserServiceFacade;
import com.emerson.userinfoservice.service.user.delete.DeleteUserServiceFacade;
import com.emerson.userinfoservice.service.user.dto.UserDto;
import com.emerson.userinfoservice.service.user.read.FindUserServiceFacade;
import com.emerson.userinfoservice.service.user.update.UpdateUserServiceFacade;
import com.emerson.userinfoservice.service.user.update.dto.UpdatePersonalInfoRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/internal/v1/user")
@CrossOrigin(origins = "*")
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
	public ResponseEntity<UserInfo> register(@RequestBody @Valid UserDto userDto) {
		return new ResponseEntity<>(this.createUserService.create(userDto), HttpStatus.CREATED);
	}

	// ADMIN
	@PreAuthorize("hasAuthority('users:get')")
	@GetMapping("/{id}")
	public ResponseEntity<UserInfo> get(@PathVariable String id, @AuthenticationPrincipal Jwt jwt, Authentication authentication) {
		System.out.println("[user-info-service] token: " + jwt.getTokenValue());
		System.out.println("[user-info-service] token authorities: " + authentication.getAuthorities());
		System.out.println("[user-info-service] token realm_access: " + jwt.getClaimAsStringList("realm_access"));
		System.out.println("[user-info-service] token claims: " + jwt.getClaims());
		
		return ResponseEntity.ok(this.findUserService.getById(id));
	}

	@PreAuthorize("hasAuthority('users:update')")
	@PutMapping("/{id}/personal-info")
	public ResponseEntity<UserInfo> updatePersonalInfo(@PathVariable String id, @RequestBody @Valid UpdatePersonalInfoRequest personalInfoRequest) {
		return ResponseEntity.ok(this.updateUserService.updatePersonalInfo(id, personalInfoRequest));
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
