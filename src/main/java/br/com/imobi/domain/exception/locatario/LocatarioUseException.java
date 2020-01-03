package br.com.imobi.domain.exception.locatario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.imobi.domain.exception.EntityUseException;

@ResponseStatus(HttpStatus.CONFLICT)
public class LocatarioUseException extends EntityUseException {
	
	private static final long serialVersionUID = 1L;

	public LocatarioUseException(String mensagem) {
		super(mensagem);
	}
	
	public LocatarioUseException(Long id) {
		this(String.format("Locatario de código %d não pode ser removida, pois está em uso", id));
	}

}