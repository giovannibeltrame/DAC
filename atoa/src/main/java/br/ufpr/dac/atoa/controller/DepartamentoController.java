package br.ufpr.dac.atoa.controller;

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

import br.ufpr.dac.atoa.entity.Departamento;
import br.ufpr.dac.atoa.service.DepartamentoService;

@SuppressWarnings("rawtypes")
@RestController
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping("/departamentos")
	public ResponseEntity get() throws Exception {
		List<Departamento> list = this.departamentoService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@GetMapping("/departamentos/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		Departamento d = this.departamentoService.findOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(d);
	}

	@PostMapping("/departamentos")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		Departamento d = new ObjectMapper().readValue(body, Departamento.class);
		d = this.departamentoService.save(d);
		return ResponseEntity.status(HttpStatus.OK).body(d);
	}

	@PutMapping("/departamentos")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		Departamento d = new ObjectMapper().readValue(body, Departamento.class);
		d = this.departamentoService.save(d);
		return ResponseEntity.status(HttpStatus.OK).body(d);
	}

	@DeleteMapping("/departamentos/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.departamentoService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

}