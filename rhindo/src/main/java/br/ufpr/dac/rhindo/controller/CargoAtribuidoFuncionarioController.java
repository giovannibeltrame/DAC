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

import br.ufpr.dac.rhindo.entity.CargoAtribuidoFuncionario;
import br.ufpr.dac.rhindo.service.CargoAtribuidoFuncionarioService;

@SuppressWarnings("rawtypes")
@RestController
public class CargoAtribuidoFuncionarioController {

	@Autowired
	private CargoAtribuidoFuncionarioService cargoAtribuidoFuncionarioService;
	
	@GetMapping("/cargo-atribuido-funcionario/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		CargoAtribuidoFuncionario caf = this.cargoAtribuidoFuncionarioService.find(id);
		return ResponseEntity.status(HttpStatus.OK).body(caf);
	}
	
	@GetMapping("/cargo-atribuido-funcionario")
	public ResponseEntity get() throws Exception {
		List<CargoAtribuidoFuncionario> list = this.cargoAtribuidoFuncionarioService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@GetMapping("/cargo-atribuido-funcionario/get")
	public ResponseEntity get(@RequestBody String body) throws Exception {
		CargoAtribuidoFuncionario caf = new ObjectMapper().readValue(body, CargoAtribuidoFuncionario.class);
		caf = this.cargoAtribuidoFuncionarioService.findOne(caf.getId());
		return ResponseEntity.status(HttpStatus.OK).body(caf);
	}

	@PostMapping("/cargo-atribuido-funcionario")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		CargoAtribuidoFuncionario caf = new ObjectMapper().readValue(body, CargoAtribuidoFuncionario.class);
		caf = this.cargoAtribuidoFuncionarioService.insert(caf);
		return ResponseEntity.status(HttpStatus.OK).body(caf);
	}

	@DeleteMapping("/cargo-atribuido-funcionario")
	public ResponseEntity delete(@RequestBody String body) throws Exception {
		CargoAtribuidoFuncionario caf = new ObjectMapper().readValue(body, CargoAtribuidoFuncionario.class);
		this.cargoAtribuidoFuncionarioService.delete(caf.getId());
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
}
