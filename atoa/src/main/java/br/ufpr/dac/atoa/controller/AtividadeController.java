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

import br.ufpr.dac.atoa.entity.Atividade;
import br.ufpr.dac.atoa.exception.ResourceNotFoundException;
import br.ufpr.dac.atoa.service.AtividadeService;

@RestController
public class AtividadeController {

	@Autowired
	private AtividadeService atividadeService;
	
	@GetMapping("/atividades")
	public ResponseEntity<List<Atividade>> get() {
		List<Atividade> list = null;
		try {
			list = this.atividadeService.findAll();
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Atividade>>(list, HttpStatus.OK);
	}

	@GetMapping("/atividades/{id}")
	public ResponseEntity<Atividade> get(@PathVariable Long id) {
		Atividade a = null;
		try {
			a = this.atividadeService.findOne(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Atividade>(a, HttpStatus.OK);
	}
	
	@PostMapping("/atividades")
	public ResponseEntity<Atividade> post(@RequestBody String body) {
		Atividade a = null;
		try {
			a = new ObjectMapper().readValue(body, Atividade.class);
			a = this.atividadeService.save(a);
		} catch (JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Atividade>(a, HttpStatus.OK);
	}
	
	@PutMapping("/atividades")
	public ResponseEntity<Atividade> put(@RequestBody String body) {
		Atividade a = null;
		try {
			a = new ObjectMapper().readValue(body, Atividade.class);
			a = this.atividadeService.save(a);
		} catch (JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Atividade>(a, HttpStatus.OK);
	}
	
	@DeleteMapping("/atividades/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		try {
			this.atividadeService.delete(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
