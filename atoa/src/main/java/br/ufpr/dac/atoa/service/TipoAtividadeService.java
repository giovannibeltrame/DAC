package br.ufpr.dac.atoa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpr.dac.atoa.entity.TipoAtividade;
import br.ufpr.dac.atoa.exception.ResourceNotFoundException;
import br.ufpr.dac.atoa.repository.TipoAtividadeRepository;

@Service
public class TipoAtividadeService {

	@Autowired
	private TipoAtividadeRepository tipoAtividadeRepository;
	
	public List<TipoAtividade> findAll() throws Exception {
		List<TipoAtividade> list = this.tipoAtividadeRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return list;
	}
	
	public TipoAtividade findOne(Long id) throws Exception {
		TipoAtividade a = this.tipoAtividadeRepository.findOne(id);
		if (a == null) {
			throw new ResourceNotFoundException();
		}
		return a;
	}
	
	public TipoAtividade save(TipoAtividade a) throws Exception {
		a = this.tipoAtividadeRepository.saveAndFlush(a);
		return a;
	}
	
	public void delete(Long id) throws Exception {
		this.tipoAtividadeRepository.delete(id);
	}
}
