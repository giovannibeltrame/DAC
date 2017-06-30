package br.ufpr.dac.rhindo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.dac.rhindo.entity.Cargo;
import br.ufpr.dac.rhindo.exception.ResourceNotFoundException;
import br.ufpr.dac.rhindo.repository.CargoRepository;

@Service
public class CargoService {
	
	@Autowired
	private CargoRepository cargoRepository;

	public List<Cargo> findAll() throws Exception {
		List<Cargo> list = this.cargoRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list; 
	}

	public Cargo findOne(Long id) throws Exception {
		Cargo c = this.cargoRepository.findOne(id);
		if (c == null) {
			throw new ResourceNotFoundException();
		}		
		return c;
	}

	public Cargo save(Cargo c) throws Exception {
		c = this.cargoRepository.saveAndFlush(c);
		return c;
	}
	
	public void delete(Long id) throws Exception {
		this.cargoRepository.delete(id);
	}
}
