package com.emerson.userinfoservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Representa a entidade de domínio {@code User}, que contém informações
 * básicas de identificação de um usuário no sistema.
 * 
 * <p>Essa classe é utilizada nas camadas de domínio e aplicação, geralmente
 * como retorno de operações envolvendo criação, atualização, leitura ou
 * deleção de usuários.</p>
 */
@Data
@AllArgsConstructor
public class User {

    /**
     * Identificador único do usuário.
     * 
     * <p>Geralmente definido pelo serviço de autenticação ou pela aplicação cliente.</p>
     */
	private String id;

    /**
     * Indica se o usuário está ativo ou inativo no sistema.
     * 
     * <p>Esse campo é ignorado durante a serialização JSON, pois representa um
     * dado interno de controle.</p>
     */
	@JsonIgnore
	private Boolean active;

    /**
     * Nome completo do usuário.
     * 
     * <p>Utilizado para exibição e identificação pessoal do usuário no sistema.</p>
     */
	private String fullName;

    /**
     * CPF do usuário (Cadastro de Pessoa Física).
     * 
     * <p>Campo sensível e único que também pode ser usado para validações ou autenticações específicas.</p>
     */
	private String cpf;

}
