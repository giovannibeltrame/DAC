package br.ufpr.dac.rhindo.controller;

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

import br.ufpr.dac.rhindo.entity.Cidade;
import br.ufpr.dac.rhindo.exception.ResourceNotFoundException;
import br.ufpr.dac.rhindo.service.CidadeService;

@RestController
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;

	@GetMapping("/cidades")
	public ResponseEntity<List<Cidade>> get() {
		List<Cidade> list = null;
		try {
			list = this.cidadeService.findAll();
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Cidade>>(list, HttpStatus.OK);
	}

	@GetMapping("/cidades/{id}")
	public ResponseEntity<Cidade> get(@PathVariable Long id) {
		Cidade c = null;
		try {
			c = this.cidadeService.findOne(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Cidade>(c, HttpStatus.OK);
	}
	
	@PostMapping("/cidades")
	public ResponseEntity<Cidade> post(@RequestBody String body) {
		Cidade c = null;
		try {
			c = new ObjectMapper().readValue(body, Cidade.class);
			c = this.cidadeService.save(c);
		} catch (JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Cidade>(c, HttpStatus.OK);
	}
	
	@PutMapping("/cidades")
	public ResponseEntity<Cidade> put(@RequestBody String body) {
		Cidade c = null;
		try {
			c = new ObjectMapper().readValue(body, Cidade.class);
			c = this.cidadeService.save(c);
		} catch (JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Cidade>(c, HttpStatus.OK);
	}
	
	@DeleteMapping("/cidades/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		try {
			this.cidadeService.delete(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}