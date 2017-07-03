package br.ufpr.dac.atoa.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufpr.dac.atoa.entity.TipoAtividade;
import br.ufpr.dac.atoa.exception.ResourceNotFoundException;
import br.ufpr.dac.atoa.service.TipoAtividadeService;

@RestController
public class TipoAtividadeController {

	@Autowired
	private TipoAtividadeService tipoAtividadeService;
	
	@GetMapping("/tiposatividades")
	public ResponseEntity<List<TipoAtividade>> get() {
		List<TipoAtividade> list = null;
		try {
			list = this.tipoAtividadeService.findAll();
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<TipoAtividade>>(list, HttpStatus.OK);
	}

	@GetMapping("/tiposatividades/{id}")
	public ResponseEntity<TipoAtividade> get(@PathVariable Long id) {
		TipoAtividade t = null;
		try {
			t = this.tipoAtividadeService.findOne(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<TipoAtividade>(t, HttpStatus.OK);
	}
	
	@PostMapping("/tiposatividades")
	public ResponseEntity<TipoAtividade> post(@RequestBody String body) {
		TipoAtividade t = null;
		try {
			t = new ObjectMapper().readValue(body, TipoAtividade.class);
			t = this.tipoAtividadeService.save(t);
		} catch (JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<TipoAtividade>(t, HttpStatus.OK);
	}
	
	@PutMapping("/tiposatividades")
	public ResponseEntity<TipoAtividade> put(@RequestBody String body) {
		TipoAtividade t = null;
		try {
			t = new ObjectMapper().readValue(body, TipoAtividade.class);
			t = this.tipoAtividadeService.save(t);
		} catch (JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<TipoAtividade>(t, HttpStatus.OK);
	}
	
	@DeleteMapping("/tiposatividades/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		try {
			this.tipoAtividadeService.delete(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
