package br.ufpr.dac.rhindo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.ufpr.dac.rhindo.entity.Funcionario;
import br.ufpr.dac.rhindo.exception.BusinessException;
import br.ufpr.dac.rhindo.exception.IntegrationException;
import br.ufpr.dac.rhindo.exception.ResourceNotFoundException;
import br.ufpr.dac.rhindo.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	private static final String URL_ATOA = "http://localhost:8070/funcionarios/";

	public List<Funcionario> findAll() throws Exception {
		List<Funcionario> list = this.funcionarioRepository.find();
		return list;
	}

	public Funcionario findOne(Long id) throws Exception {
		Funcionario f = this.funcionarioRepository.findOne(id);
		if (f == null) {
			throw new ResourceNotFoundException();
		}
		return f;
	}

	public Funcionario insert(Funcionario f) throws Exception {
		if (this.funcionarioRepository.findByCpf(f.getCpf()) != null) {
			throw new BusinessException("O funcionário com o CPF informado já existe. CPF: " + f.getCpf());
		}
		if (this.funcionarioRepository.findByRg(f.getRg()) != null) {
			throw new BusinessException("O funcionário com o RG informado já existe. RG: " + f.getRg());
		}
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Funcionario> response = restTemplate.postForEntity(URL_ATOA, f, Funcionario.class);
		switch (response.getStatusCode()) {
		case OK:
			f.setId(response.getBody().getId());
			f = this.funcionarioRepository.saveAndFlush(f);
			break;
		default:
			throw new IntegrationException(response.toString());
		}
		return f;
	}
	
	public Funcionario update(Funcionario f) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(URL_ATOA, f);
		return this.funcionarioRepository.saveAndFlush(f);
	}

	public void delete(Long id) throws Exception {
		Funcionario f = this.findOne(id);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(URL_ATOA + id);
		this.funcionarioRepository.delete(f);
	}
}
