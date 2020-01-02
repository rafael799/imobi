package br.com.imobi.application.exceptionhandler;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiHandler {
	
	private LocalDateTime dateTimer;
	private String message;

}
