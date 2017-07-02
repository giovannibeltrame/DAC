package br.ufpr.dac.rhindo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.dac.rhindo.entity.Funcionario;
import br.ufpr.dac.rhindo.exception.ResourceNotFoundException;
import br.ufpr.dac.rhindo.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public List<Funcionario> findAll() throws Exception {
		List<Funcionario> list = this.funcionarioRepository.findAll();
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
		SimpleMD5 md5 = new SimpleMD5("brq", f.getSenha());
		f = this.funcionarioRepository.saveAndFlush(f);
		return f;
	}
	
	public void delete(Long id) throws Exception {
		this.funcionarioRepository.delete(id);
	}
}
