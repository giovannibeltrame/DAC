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

import br.ufpr.dac.rhindo.entity.Cargo;
import br.ufpr.dac.rhindo.exception.ResourceNotFoundException;
import br.ufpr.dac.rhindo.service.CargoService;

@RestController
public class CargoController {

	@Autowired
	private CargoService cargoService;

	@GetMapping("/cargos")
	public ResponseEntity<List<Cargo>> get() {
		List<Cargo> list = null;
		try {
			list = this.cargoService.findAll();
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Cargo>>(list, HttpStatus.OK);
	}

	@GetMapping("/cargo/{id}")
	public ResponseEntity<Cargo> get(@PathVariable Long id) {
		Cargo c = null;
		try {
			c = this.cargoService.findOne(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Cargo>(c, HttpStatus.OK);
	}
	
	@PostMapping("/cargos")
	public ResponseEntity<Cargo> post(@RequestBody String body) {
		Cargo c = null;
		try {
			c = new ObjectMapper().readValue(body, Cargo.class);
			c = this.cargoService.save(c);
		} catch (JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Cargo>(c, HttpStatus.OK);
	}
	
	@PutMapping("/cargos")
	public ResponseEntity<Cargo> put(@RequestBody String body) {
		Cargo c = null;
		try {
			c = new ObjectMapper().readValue(body, Cargo.class);
			c = this.cargoService.save(c);
		} catch (JsonParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (JsonMappingException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Cargo>(c, HttpStatus.OK);
	}
	
	@DeleteMapping("/cargos/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		try {
			this.cargoService.delete(id);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}