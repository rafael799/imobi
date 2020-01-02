package br.com.imobi.domain.exception.contrato;

import br.com.imobi.domain.exception.EntityNotFoundException;

public class ContratoNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public ContratoNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public ContratoNotFoundException(Long id) {
		this(String.format("Não existe um cadastro de Contrato com código %d", id));
	}
	

}
