package br.ufpr.dac.rhindo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.dac.rhindo.entity.Requisito;
import br.ufpr.dac.rhindo.exception.ResourceNotFoundException;
import br.ufpr.dac.rhindo.repository.RequisitoRepository;

@Service
public class RequisitoService {
	
	@Autowired
	private RequisitoRepository requisitoRepository;

	public List<Requisito> findAll() throws Exception {
		List<Requisito> list = this.requisitoRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list; 
	}

	public Requisito findOne(Long id) throws Exception {
		Requisito r = this.requisitoRepository.findOne(id);
		if (r == null) {
			throw new ResourceNotFoundException();
		}		
		return r;
	}

	public Requisito save(Requisito r) throws Exception {
		r = this.requisitoRepository.saveAndFlush(r);
		return r;
	}
	
	public void delete(Long id) throws Exception {
		this.requisitoRepository.delete(id);
	}
}
