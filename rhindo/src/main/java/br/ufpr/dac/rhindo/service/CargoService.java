package br.ufpr.dac.rhindo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.ufpr.dac.rhindo.entity.Cargo;
import br.ufpr.dac.rhindo.exception.IntegrationException;
import br.ufpr.dac.rhindo.exception.ResourceNotFoundException;
import br.ufpr.dac.rhindo.repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;
	private static final String URL_ATOA = "http://localhost:8070/cargos/";

	public List<Cargo> findAll() throws Exception {
		List<Cargo> list = this.cargoRepository.findAll();
		return list;
	}

	public Cargo findOne(Long id) throws Exception {
		Cargo c = this.cargoRepository.findOne(id);
		if (c == null) {
			throw new ResourceNotFoundException();
		}
		return c;
	}

	public Cargo insert(Cargo c) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Cargo> response = restTemplate.postForEntity(URL_ATOA, c, Cargo.class);
		switch (response.getStatusCode()) {
		case OK:
			c.setId(response.getBody().getId());
			c = this.cargoRepository.saveAndFlush(c);
			break;
		default:
			throw new IntegrationException(response.toString());
		}
		return c;
	}

	public Cargo update(Cargo c) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(URL_ATOA, c);
		return this.cargoRepository.saveAndFlush(c);
	}

	public void delete(Long id) throws Exception {
		Cargo c = this.findOne(id);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(URL_ATOA + id);
		this.cargoRepository.delete(c);
	}
}
