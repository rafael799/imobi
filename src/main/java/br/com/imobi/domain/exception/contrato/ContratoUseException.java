package br.com.imobi.domain.exception.contrato;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.imobi.domain.exception.EntityUseException;

@ResponseStatus(HttpStatus.CONFLICT)
public class ContratoUseException extends EntityUseException {
	
	private static final long serialVersionUID = 1L;

	public ContratoUseException(String mensagem) {
		super(mensagem);
	}
	
	public ContratoUseException(Long id) {
		this(String.format("Contrato de código %d não pode ser removida, pois está em uso", id));
	}

}