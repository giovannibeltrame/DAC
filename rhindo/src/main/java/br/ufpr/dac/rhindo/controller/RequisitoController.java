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

import br.ufpr.dac.rhindo.entity.Requisito;
import br.ufpr.dac.rhindo.exception.ResourceNotFoundException;
import br.ufpr.dac.rhindo.service.RequisitoService;

@RestController
public class RequisitoController {

	@Autowired
	private RequisitoService requisitoService;

	@GetMapping("/requisitos")
	public ResponseEntity<List<Requisito>> get() {
		List<Requisito> list = null;
		try {
			list = this.requisitoService.findAll();
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Requisito>>(list, HttpStatus.OK);
	}

	@GetMapping("/requisitos/{id}")
	public ResponseEntity<Requisito> get(@PathVariable Long id) {
		Requisito r = null;
		try {
			r = this.requisitoService.findOne(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Requisito>(r, HttpStatus.OK);
	}
	
	@PostMapping("/requisitos")
	public ResponseEntity<Requisito> post(@RequestBody String body) {
		Requisito r = null;
		try {
			r = new ObjectMapper().readValue(body, Requisito.class);
			r = this.requisitoService.save(r);
		} catch (JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Requisito>(r, HttpStatus.OK);
	}
	
	@PutMapping("/requisitos")
	public ResponseEntity<Requisito> put(@RequestBody String body) {
		Requisito r = null;
		try {
			r = new ObjectMapper().readValue(body, Requisito.class);
			r = this.requisitoService.save(r);
		} catch (JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Requisito>(r, HttpStatus.OK);
	}
	
	@DeleteMapping("/requisitos/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		try {
			this.requisitoService.delete(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}