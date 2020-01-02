package br.com.imobi.domain.exception.locador;

import br.com.imobi.domain.exception.EntityNotFoundException;

public class LocadorNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public LocadorNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public LocadorNotFoundException(Long id) {
		this(String.format("Não existe um cadastro de Locador com código %d", id));
	}
	

}
