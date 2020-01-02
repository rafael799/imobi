package br.com.imobi.application.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.imobi.domain.exception.EntityNotFoundException;
import br.com.imobi.domain.exception.EntityUseException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handlerEntidadeNaoEncontradaException(EntityNotFoundException e) {
		ApiHandler apiHandler = ApiHandler.builder().dateTimer(LocalDateTime.now()).message(e.getMessage()).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiHandler);
	}

	@ExceptionHandler(EntityUseException.class)
	public ResponseEntity<?> handlerUseException(EntityUseException e) {
		ApiHandler apiHandler = ApiHandler.builder().dateTimer(LocalDateTime.now()).message(e.getMessage()).build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiHandler);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> handlerHttpMediaTypeNotSupportedException() {
		ApiHandler apiHandler = ApiHandler.builder().dateTimer(LocalDateTime.now()).message("O tipo de mídia não é aceito.").build();
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(apiHandler);
	}

}
