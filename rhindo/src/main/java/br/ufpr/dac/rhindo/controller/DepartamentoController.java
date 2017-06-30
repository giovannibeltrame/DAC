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

import br.ufpr.dac.rhindo.entity.Departamento;
import br.ufpr.dac.rhindo.exception.ResourceNotFoundException;
import br.ufpr.dac.rhindo.service.DepartamentoService;

@RestController
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping("/departamentos")
	public ResponseEntity<List<Departamento>> get() {
		List<Departamento> list = null;
		try {
			list = this.departamentoService.findAll();
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Departamento>>(list, HttpStatus.OK);
	}

	@GetMapping("/departamentos/{id}")
	public ResponseEntity<Departamento> get(@PathVariable Long id) {
		Departamento d = null;
		try {
			d = this.departamentoService.findOne(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Departamento>(d, HttpStatus.OK);
	}
	
	@PostMapping("/departamentos")
	public ResponseEntity<Departamento> post(@RequestBody String body) {
		Departamento d = null;
		try {
			d = new ObjectMapper().readValue(body, Departamento.class);
			d = this.departamentoService.save(d);
		} catch (JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Departamento>(d, HttpStatus.OK);
	}
	
	@PutMapping("/departamentos")
	public ResponseEntity<Departamento> put(@RequestBody String body) {
		Departamento d = null;
		try {
			d = new ObjectMapper().readValue(body, Departamento.class);
			d = this.departamentoService.save(d);
		} catch (JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Departamento>(d, HttpStatus.OK);
	}
	
	@DeleteMapping("/departamentos/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		try {
			this.departamentoService.delete(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}