package br.ufpr.dac.rhindo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.dac.rhindo.entity.Cidade;
import br.ufpr.dac.rhindo.exception.ResourceNotFoundException;
import br.ufpr.dac.rhindo.repository.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;

	public List<Cidade> findAll() throws Exception {
		List<Cidade> list = this.cidadeRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list; 
	}

	public Cidade findOne(Long id) throws Exception {
		Cidade c = this.cidadeRepository.findOne(id);
		if (c == null) {
			throw new ResourceNotFoundException();
		}		
		return c;
	}

	public Cidade save(Cidade c) throws Exception {
		c = this.cidadeRepository.saveAndFlush(c);
		return c;
	}
	
	public void delete(Long id) throws Exception {
		this.cidadeRepository.delete(id);
	}
}
