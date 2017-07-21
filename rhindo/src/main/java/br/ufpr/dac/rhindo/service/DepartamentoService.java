package br.ufpr.dac.rhindo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.ufpr.dac.rhindo.entity.Departamento;
import br.ufpr.dac.rhindo.exception.IntegrationException;
import br.ufpr.dac.rhindo.exception.ResourceNotFoundException;
import br.ufpr.dac.rhindo.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository departamentoRepository;
	private static final String URL_ATOA = "http://localhost:8070/departamentos/";

	public List<Departamento> findAll() throws Exception {
		List<Departamento> list = this.departamentoRepository.findAll();
		return list;
	}

	public Departamento findOne(Long id) throws Exception {
		Departamento d = this.departamentoRepository.findOne(id);
		if (d == null) {
			throw new ResourceNotFoundException();
		}
		return d;
	}

	public Departamento insert(Departamento d) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Departamento> response = restTemplate.postForEntity(URL_ATOA, d, Departamento.class);
		switch (response.getStatusCode()) {
		case OK:
			d.setId(response.getBody().getId());
			d = this.departamentoRepository.saveAndFlush(d);
			break;
		default:
			throw new IntegrationException(response.toString());
		}
		return d;
	}

	public Departamento update(Departamento d) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(URL_ATOA, d);
		return this.departamentoRepository.saveAndFlush(d);
	}

	public void delete(Long id) throws Exception {
		Departamento d = this.findOne(id);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(URL_ATOA + id);
		this.departamentoRepository.delete(d);
	}
}
