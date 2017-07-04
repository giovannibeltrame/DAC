package br.ufpr.dac.rhindo.controller;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufpr.dac.rhindo.entity.Cargo;
import br.ufpr.dac.rhindo.service.CargoService;

@SuppressWarnings("rawtypes")
@RestController
public class CargoController {

	@Autowired
	private CargoService cargoService;

	@GetMapping("/cargos")
	public ResponseEntity get() throws Exception {
		List<Cargo> list = this.cargoService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@GetMapping("/cargo/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		Cargo c = this.cargoService.findOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(c);
	}

	@PostMapping("/cargos")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		Cargo c = new ObjectMapper().readValue(body, Cargo.class);
		c = this.cargoService.insert(c);
		return ResponseEntity.status(HttpStatus.OK).body(c);
	}

	@PutMapping("/cargos")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		Cargo c = new ObjectMapper().readValue(body, Cargo.class);
		c = this.cargoService.update(c);
		return ResponseEntity.status(HttpStatus.OK).body(c);
	}

	@DeleteMapping("/cargos/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.cargoService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}