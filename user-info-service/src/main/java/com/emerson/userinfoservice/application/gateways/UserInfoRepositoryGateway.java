package com.emerson.userinfoservice.application.gateways;

import java.util.Optional;

import com.emerson.userinfoservice.domain.model.User;

/**
 * Interface que define o contrato de acesso aos dados de {@link User}
 * na camada de repositório.
 * 
 * Essa interface atua como um gateway entre o domínio e a infraestrutura de persistência,
 * permitindo que a lógica de negócio interaja com os dados de forma desacoplada.
 * 
 * Implementações dessa interface são responsáveis por persistir, buscar e remover 
 * entidades de usuário no mecanismo de armazenamento subjacente (ex: banco de dados).
 */
public interface UserInfoRepositoryGateway {

	User save(User user);
	
	boolean existsById(String id);
	boolean existsByCpf(String cpf);

	Optional<User> findById(String id);
	
	void deleteById(String id);
	
}
