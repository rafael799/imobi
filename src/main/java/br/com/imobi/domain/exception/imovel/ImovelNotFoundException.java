package br.com.imobi.domain.exception.imovel;

import br.com.imobi.domain.exception.EntityNotFoundException;

public class ImovelNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;

	public ImovelNotFoundException(String code) {
		super(String.format("Não existe um cadastro de Imovel com código %s", code));
	}
	

}
