package br.ufpr.dac.rhindo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.dac.rhindo.entity.UnidadeFederativa;
import br.ufpr.dac.rhindo.exception.ResourceNotFoundException;
import br.ufpr.dac.rhindo.repository.UnidadeFederativaRepository;

@Service
public class UnidadeFederativaService {
	
	@Autowired
	private UnidadeFederativaRepository unidadeFederativaRepository;

	public List<UnidadeFederativa> findAll() throws Exception {
		List<UnidadeFederativa> list = this.unidadeFederativaRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list; 
	}

	public UnidadeFederativa findOne(Long id) throws Exception {
		UnidadeFederativa uf = this.unidadeFederativaRepository.findOne(id);
		if (uf == null) {
			throw new ResourceNotFoundException();
		}		
		return uf;
	}

	public UnidadeFederativa save(UnidadeFederativa uf) throws Exception {
		uf = this.unidadeFederativaRepository.saveAndFlush(uf);
		return uf;
	}
	
	public void delete(Long id) throws Exception {
		this.unidadeFederativaRepository.delete(id);
	}
}
