package com.emerson.authservice.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa um papel (role) de usuário no sistema, que pode ser utilizado para controle de permissões.
 * 
 * <p>Possui um identificador único e um nome.</p>
 * 
 * <p>Também define os valores padrão possíveis para papéis no sistema:</p>
 * <ul>
 *   <li>USER - Usuário comum</li>
 *   <li>ADMIN - Usuário administrador</li>
 * </ul>
 */
@Data
@NoArgsConstructor
public class Role {

	/** Identificador único do papel. */
	private String id;
	
	/** Nome do papel, por exemplo, "USER" ou "ADMIN". */
	private String name;

	/**
	 * Enumeração contendo valores constantes dos papéis disponíveis no sistema.
	 */
	public enum Values {
		USER, 
		ADMIN
	}

	/**
	 * Construtor para criar um papel com um nome específico.
	 * 
	 * @param name nome do papel
	 */
	public Role(String name) {
		this.name = name;
	}

}
