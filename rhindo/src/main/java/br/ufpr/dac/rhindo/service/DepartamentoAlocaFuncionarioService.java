package br.ufpr.dac.rhindo.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.ufpr.dac.rhindo.entity.DepartamentoAlocaFuncionario;
import br.ufpr.dac.rhindo.entity.DepartamentoAlocaFuncionarioPK;
import br.ufpr.dac.rhindo.exception.ResourceNotFoundException;
import br.ufpr.dac.rhindo.repository.DepartamentoAlocaFuncionarioRepository;

@Service
public class DepartamentoAlocaFuncionarioService {

	@Autowired
	private DepartamentoAlocaFuncionarioRepository departamentoAlocaFuncionarioRepository;
	private static final String URL_ATOA = "http://localhost:8070/funcionarios/";

	public List<DepartamentoAlocaFuncionario> findAll() throws Exception {
		List<DepartamentoAlocaFuncionario> list = this.departamentoAlocaFuncionarioRepository.findAll();
		return list;
	}

	public DepartamentoAlocaFuncionario findOne(DepartamentoAlocaFuncionarioPK id) throws Exception {
		DepartamentoAlocaFuncionario daf = this.departamentoAlocaFuncionarioRepository.findOne(id);
		if (daf == null) {
			throw new ResourceNotFoundException();
		}
		return daf;
	}

	public DepartamentoAlocaFuncionario insert(DepartamentoAlocaFuncionario daf) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		JSONObject json = new JSONObject();
		json.put("iddepartamento", daf.getDepartamento().getId());
		json.put("idfuncionario", daf.getFuncionario().getId());
		restTemplate.put(URL_ATOA + "cargo", json);
		return this.departamentoAlocaFuncionarioRepository.saveAndFlush(daf);
	}

	public DepartamentoAlocaFuncionario update(DepartamentoAlocaFuncionario daf) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		JSONObject json = new JSONObject();
		json.put("iddepartamento", "");
		json.put("idfuncionario", daf.getFuncionario().getId());
		restTemplate.put(URL_ATOA + "cargo", json);
		return this.departamentoAlocaFuncionarioRepository.saveAndFlush(daf);
	}

	public void delete(DepartamentoAlocaFuncionarioPK id) throws Exception {
		DepartamentoAlocaFuncionario daf = this.departamentoAlocaFuncionarioRepository.findOne(id);
		this.departamentoAlocaFuncionarioRepository.delete(daf);
	}
}
