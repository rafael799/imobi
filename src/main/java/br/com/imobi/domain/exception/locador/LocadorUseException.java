package br.com.imobi.domain.exception.locador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.imobi.domain.exception.EntityUseException;

@ResponseStatus(HttpStatus.CONFLICT)
public class LocadorUseException extends EntityUseException {
	
	private static final long serialVersionUID = 1L;

	public LocadorUseException(String mensagem) {
		super(mensagem);
	}
	
	public LocadorUseException(Long id) {
		this(String.format("Locador de código %d não pode ser removida, pois está em uso", id));
	}

}