package br.ufpr.dac.rhindo.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@SuppressWarnings("rawtypes")
@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleException(ResourceNotFoundException e) {
        // log exception
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("O recurso informado na requisição não foi encontrado.");
    }
	
	@ExceptionHandler({JsonParseException.class, JsonMappingException.class, IOException.class})
    public ResponseEntity handleException() {
        // log exception
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Parâmetros inválidos.");
    }
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception e) {
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Sistema temporariamente indisponível. Tente novamente mais tarde.");
	}
}
