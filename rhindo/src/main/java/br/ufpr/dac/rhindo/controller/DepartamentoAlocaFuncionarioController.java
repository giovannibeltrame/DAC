package br.ufpr.dac.rhindo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufpr.dac.rhindo.entity.DepartamentoAlocaFuncionario;
import br.ufpr.dac.rhindo.service.DepartamentoAlocaFuncionarioService;

@SuppressWarnings("rawtypes")
@RestController
public class DepartamentoAlocaFuncionarioController {

	@Autowired
	private DepartamentoAlocaFuncionarioService departamentoAlocaFuncionarioService;

	@GetMapping("/departamento-aloca-funcionario")
	public ResponseEntity get() throws Exception {
		List<DepartamentoAlocaFuncionario> list = this.departamentoAlocaFuncionarioService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@GetMapping("/departamento-aloca-funcionario/get")
	public ResponseEntity get(@RequestBody String body) throws Exception {
		DepartamentoAlocaFuncionario daf = new ObjectMapper().readValue(body, DepartamentoAlocaFuncionario.class);
		daf = this.departamentoAlocaFuncionarioService.findOne(daf.getId());
		return ResponseEntity.status(HttpStatus.OK).body(daf);
	}

	@PostMapping("/departamento-aloca-funcionario")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		DepartamentoAlocaFuncionario daf = new ObjectMapper().readValue(body, DepartamentoAlocaFuncionario.class);
		daf = this.departamentoAlocaFuncionarioService.insert(daf);
		return ResponseEntity.status(HttpStatus.OK).body(daf);
	}

	@PutMapping("/departamento-aloca-funcionario")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		DepartamentoAlocaFuncionario daf = new ObjectMapper().readValue(body, DepartamentoAlocaFuncionario.class);
		daf = this.departamentoAlocaFuncionarioService.update(daf);
		return ResponseEntity.status(HttpStatus.OK).body(daf);
	}

	@DeleteMapping("/departamento-aloca-funcionario")
	public ResponseEntity delete(@RequestBody String body) throws Exception {
		DepartamentoAlocaFuncionario daf = new ObjectMapper().readValue(body, DepartamentoAlocaFuncionario.class);
		this.departamentoAlocaFuncionarioService.delete(daf.getId());
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
}
