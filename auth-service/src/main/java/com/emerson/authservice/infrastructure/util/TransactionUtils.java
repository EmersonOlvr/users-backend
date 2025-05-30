package com.emerson.authservice.infrastructure.util;

import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Utilitário para execução de ações após a conclusão bem-sucedida de uma transação.
 * 
 * <p>Essa classe fornece um método auxiliar para garantir que determinada ação
 * seja executada somente após o commit da transação atual. Caso não exista uma
 * transação ativa, a ação é executada imediatamente.</p>
 */
public class TransactionUtils {

    /**
     * Executa a ação fornecida somente após o commit bem-sucedido da transação atual.
     *
     * <p>Se não houver uma transação ativa, a ação é executada imediatamente.</p>
     *
     * @param action a ação a ser executada após o commit da transação
     */
	public static void runAfterCommit(Runnable action) {
		if (TransactionSynchronizationManager.isSynchronizationActive()) {
			TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
				@Override
				public void afterCommit() {
					action.run();
				}
			});
		} else {
			// se não estiver dentro de uma transação, execute imediatamente
			action.run();
		}
	}

}
