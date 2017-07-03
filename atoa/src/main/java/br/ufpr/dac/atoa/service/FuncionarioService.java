package br.ufpr.dac.atoa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.dac.atoa.entity.Funcionario;
import br.ufpr.dac.atoa.exception.ResourceNotFoundException;
import br.ufpr.dac.atoa.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public List<Funcionario> findAll() throws Exception {
		List<Funcionario> list = this.funcionarioRepository.find();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list; 
	}

	public Funcionario findOne(Long id) throws Exception {
		Funcionario f = this.funcionarioRepository.findOne(id);
		if (f == null) {
			throw new ResourceNotFoundException();
		}		
		return f;
	}

	public Funcionario save(Funcionario f) throws Exception {
		f = this.funcionarioRepository.saveAndFlush(f);
		return f;
	}
	
	public void delete(Long id) throws Exception {
		this.funcionarioRepository.delete(id);
	}
}
