package br.com.imobi.domain.exception.locatario;

import br.com.imobi.domain.exception.EntityNotFoundException;

public class LocatarioNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public LocatarioNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public LocatarioNotFoundException(Long id) {
		this(String.format("Não existe um cadastro de Locatário com código %d", id));
	}
	

}
