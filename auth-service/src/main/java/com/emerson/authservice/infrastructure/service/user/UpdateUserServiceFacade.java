package com.emerson.authservice.infrastructure.service.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emerson.authservice.application.usecase.user.UpdateUserPasswordUseCase;
import com.emerson.authservice.application.usecase.user.UpdateUserRolesUseCase;
import com.emerson.authservice.application.usecase.user.UpdateUserUsernameUseCase;
import com.emerson.authservice.domain.model.User;
import com.emerson.authservice.infrastructure.messaging.kafka.producer.UserEventProducer;
import com.emerson.authservice.infrastructure.util.TransactionUtils;

import lombok.AllArgsConstructor;

/**
 * Esta classe é uma fachada responsável pelas operações de atualização de dados de usuários.
 * 
 * <p>Inclui métodos para atualização de nome de usuário, senha e permissões (roles).</p>
 */
@Service
@AllArgsConstructor
public class UpdateUserServiceFacade {
	
	private final UpdateUserUsernameUseCase updateUserUsernameUseCase;
	private final UpdateUserPasswordUseCase updateUserPasswordUseCase;
	private final UpdateUserRolesUseCase updateUserRolesUseCase;
	private final UserEventProducer userEventProducer;

	/**
	 * Atualiza o nome de usuário de um usuário existente.
	 * 
	 * <p>Este método delega a atualização ao caso de uso {@link UpdateUserUsernameUseCase}.</p>
	 *
	 * @param id          o identificador único do usuário
	 * @param newUsername o novo nome de usuário a ser definido
	 * @return o usuário atualizado
	 */
	@Transactional
	public User updateUsername(String id, String newUsername) {
		return this.updateUserUsernameUseCase.execute(id, newUsername);
	}

	/**
	 * Atualiza a senha de um usuário, validando a senha atual e a confirmação da
	 * nova senha.
	 * 
	 * <p>Este método delega a atualização ao caso de uso {@link UpdateUserPasswordUseCase}.</p>
	 *
	 * @param id                o identificador único do usuário
	 * @param currentPassword   a senha atual do usuário
	 * @param newPassword       a nova senha a ser definida
	 * @param newPasswordRepeat confirmação da nova senha
	 * @return o usuário com a senha atualizada
	 */
	@Transactional
	public User updatePassword(String id, String currentPassword, String newPassword, String newPasswordRepeat) {
		return this.updateUserPasswordUseCase.execute(id, currentPassword, newPassword, newPasswordRepeat);
	}

	/**
	 * Atualiza as permissões (roles) de um usuário.
	 *
	 * <p>Este método delega a atualização ao caso de uso {@link UpdateUserRolesUseCase}.</p>
	 * 
	 * <p>Após a atualização, um evento é publicado para refletir a alteração.</p>
	 *
	 * @param id    o identificador único do usuário
	 * @param roles lista de novas permissões a serem associadas ao usuário
	 * @return o usuário com as novas permissões
	 */
	@Transactional
	public User updateRoles(String id, List<String> roles) {
		User user = this.updateUserRolesUseCase.execute(id, roles);
		
		// publica um evento com as novas roles desse usuário
		TransactionUtils.runAfterCommit(() -> this.userEventProducer.sendUserRolesUpdatedEvent(id, roles));
		
		return user;
	}

}
