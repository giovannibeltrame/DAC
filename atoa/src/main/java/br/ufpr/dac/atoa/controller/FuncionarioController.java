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

import br.ufpr.dac.atoa.entity.Funcionario;
import br.ufpr.dac.atoa.exception.ResourceNotFoundException;
import br.ufpr.dac.atoa.service.FuncionarioService;

@RestController
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping("/funcionarios")
	public ResponseEntity<List<Funcionario>> get() {
		List<Funcionario> list = null;
		try {
			list = this.funcionarioService.findAll();
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Funcionario>>(list, HttpStatus.OK);
	}

	@GetMapping("/funcionarios/{id}")
	public ResponseEntity<Funcionario> get(@PathVariable Long id) {
		Funcionario f = null;
		try {
			f = this.funcionarioService.findOne(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Funcionario>(f, HttpStatus.OK);
	}
	
	@PostMapping("/funcionarios")
	public ResponseEntity<Funcionario> post(@RequestBody String body) {
		Funcionario f = null;
		try {
			f = new ObjectMapper().readValue(body, Funcionario.class);
			f = this.funcionarioService.save(f);
		} catch (JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Funcionario>(f, HttpStatus.OK);
	}
	
	@PutMapping("/funcionarios")
	public ResponseEntity<Funcionario> put(@RequestBody String body) {
		Funcionario f = null;
		try {
			f = new ObjectMapper().readValue(body, Funcionario.class);
			f = this.funcionarioService.save(f);
		} catch (JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Funcionario>(f, HttpStatus.OK);
	}
	
	@DeleteMapping("/funcionarios/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		try {
			this.funcionarioService.delete(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}