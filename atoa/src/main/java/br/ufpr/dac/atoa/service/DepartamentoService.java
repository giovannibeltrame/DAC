package br.ufpr.dac.atoa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.dac.atoa.entity.Departamento;
import br.ufpr.dac.atoa.exception.ResourceNotFoundException;
import br.ufpr.dac.atoa.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;

	public List<Departamento> findAll() throws Exception {
		List<Departamento> list = this.departamentoRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list; 
	}

	public Departamento findOne(Long id) throws Exception {
		Departamento d = this.departamentoRepository.findOne(id);
		if (d == null) {
			throw new ResourceNotFoundException();
		}		
		return d;
	}

	public Departamento save(Departamento d) throws Exception {
		d = this.departamentoRepository.saveAndFlush(d);
		return d;
	}
	
	public void delete(Long id) throws Exception {
		this.departamentoRepository.delete(id);
	}
}
