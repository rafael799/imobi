package br.com.imobi.domain.exception.imovel;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.imobi.domain.exception.EntityUseException;

@ResponseStatus(HttpStatus.CONFLICT)
public class ImovelUseException extends EntityUseException {
	
	private static final long serialVersionUID = 1L;

	public ImovelUseException(String mensagem) {
		super(mensagem);
	}
	
	public ImovelUseException(Long id) {
		this(String.format("Imovel de código %d não pode ser removida, pois está em uso", id));
	}

}