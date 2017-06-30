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

import br.ufpr.dac.rhindo.entity.UnidadeFederativa;
import br.ufpr.dac.rhindo.exception.ResourceNotFoundException;
import br.ufpr.dac.rhindo.service.UnidadeFederativaService;

@RestController
public class UnidadeFederativaController {

	@Autowired
	private UnidadeFederativaService unidadeFederativaService;

	@GetMapping("/ufs")
	public ResponseEntity<List<UnidadeFederativa>> get() {
		List<UnidadeFederativa> list = null;
		try {
			list = this.unidadeFederativaService.findAll();
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<UnidadeFederativa>>(list, HttpStatus.OK);
	}

	@GetMapping("/ufs/{id}")
	public ResponseEntity<UnidadeFederativa> get(@PathVariable Long id) {
		UnidadeFederativa uf = null;
		try {
			uf = this.unidadeFederativaService.findOne(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<UnidadeFederativa>(uf, HttpStatus.OK);
	}
	
	@PostMapping("/ufs")
	public ResponseEntity<UnidadeFederativa> post(@RequestBody String body) {
		UnidadeFederativa uf = null;
		try {
			uf = new ObjectMapper().readValue(body, UnidadeFederativa.class);
			uf = this.unidadeFederativaService.save(uf);
		} catch (JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<UnidadeFederativa>(uf, HttpStatus.OK);
	}
	
	@PutMapping("/ufs")
	public ResponseEntity<UnidadeFederativa> put(@RequestBody String body) {
		UnidadeFederativa uf = null;
		try {
			uf = new ObjectMapper().readValue(body, UnidadeFederativa.class);
			uf = this.unidadeFederativaService.save(uf);
		} catch (JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<UnidadeFederativa>(uf, HttpStatus.OK);
	}
	
	@DeleteMapping("/ufs/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		try {
			this.unidadeFederativaService.delete(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}