package br.ufpr.dac.atoa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.dac.atoa.entity.Atividade;
import br.ufpr.dac.atoa.exception.ResourceNotFoundException;
import br.ufpr.dac.atoa.repository.AtividadeRepository;

@Service
public class AtividadeService {

	@Autowired
	private AtividadeRepository atividadeRepository;
	
	public List<Atividade> findAll() throws Exception {
		List<Atividade> list = this.atividadeRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list;
	}
	
	public Atividade findOne(Long id) throws Exception {
		Atividade a = this.atividadeRepository.findOne(id);
		if (a == null) {
			throw new ResourceNotFoundException();
		}
		return a;
	}
	
	public Atividade save(Atividade a) throws Exception {
		a = this.atividadeRepository.saveAndFlush(a);
		return a;
	}
	
	public void delete(Long id) throws Exception {
		this.atividadeRepository.delete(id);
	}
}
