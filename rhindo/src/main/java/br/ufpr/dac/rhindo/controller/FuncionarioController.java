package br.ufpr.dac.rhindo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufpr.dac.rhindo.entity.Funcionario;
import br.ufpr.dac.rhindo.service.FuncionarioService;

@SuppressWarnings("rawtypes")
@RestController
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private ApplicationContext appContext;

	@GetMapping("/funcionarios")
	public ResponseEntity get() throws Exception {
		List<Funcionario> list = this.funcionarioService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@GetMapping("/funcionarios/{id}")
	public ResponseEntity get(@PathVariable Long id) throws Exception {
		Funcionario f = this.funcionarioService.findOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(f);
	}

	@PostMapping("/funcionarios")
	public ResponseEntity post(@RequestBody String body) throws Exception {
		Funcionario f = new ObjectMapper().readValue(body, Funcionario.class);
		f = this.funcionarioService.insert(f);
		return ResponseEntity.status(HttpStatus.OK).body(f);
	}

	@PutMapping("/funcionarios")
	public ResponseEntity put(@RequestBody String body) throws Exception {
		Funcionario f = new ObjectMapper().readValue(body, Funcionario.class);
		f = this.funcionarioService.update(f);
		return ResponseEntity.status(HttpStatus.OK).body(f);
	}

	@DeleteMapping("/funcionarios/{id}")
	public ResponseEntity delete(@PathVariable Long id) throws Exception {
		this.funcionarioService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body("");
	}
	
	@RequestMapping(path = "/funcionarioreport", method = RequestMethod.GET)
    public ModelAndView report() throws Exception {

        JasperReportsPdfView view = new JasperReportsPdfView();
        view.setUrl("classpath:reportfuncionarios.jrxml");
        view.setApplicationContext(appContext);

        Map<String, Object> params = new HashMap<>();
        params.put("datasource", funcionarioService.findAll());

        return new ModelAndView(view, params);
    }

}