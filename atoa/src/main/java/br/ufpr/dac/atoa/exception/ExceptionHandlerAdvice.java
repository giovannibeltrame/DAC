package br.ufpr.dac.atoa.exception;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SuppressWarnings("rawtypes")
@ControllerAdvice
public class ExceptionHandlerAdvice {

	private static final Logger log = Logger.getLogger(ExceptionHandlerAdvice.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity handleException(ResourceNotFoundException e) {
		log.error(e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("O recurso informado na requisição não foi encontrado.");
	}

	@ExceptionHandler({ JsonParseException.class, JsonMappingException.class, IOException.class })
	public ResponseEntity handleExceptionParse(Exception e) {
		log.error(e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parâmetros inválidos.");
	}

	@ExceptionHandler({ IntegrationException.class, ResourceAccessException.class, RestClientException.class })
	public ResponseEntity handleIntegrationException(Exception e) {
		log.error(e);
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
				.body("Ocorreu um erro na integração com o sistema de recursos humanos (RH-INDO).");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception e) {
		log.error(e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Sistema temporariamente indisponível. Tente novamente mais tarde.");
	}
}
