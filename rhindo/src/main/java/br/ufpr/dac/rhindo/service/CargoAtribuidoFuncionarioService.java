package br.ufpr.dac.rhindo.service;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.ufpr.dac.rhindo.entity.CargoAtribuidoFuncionario;
import br.ufpr.dac.rhindo.entity.CargoAtribuidoFuncionarioPK;
import br.ufpr.dac.rhindo.entity.RoleEnum;
import br.ufpr.dac.rhindo.exception.ResourceNotFoundException;
import br.ufpr.dac.rhindo.repository.CargoAtribuidoFuncionarioRepository;
import br.ufpr.dac.rhindo.repository.DepartamentoAlocaFuncionarioRepository;
import br.ufpr.dac.rhindo.repository.FuncionarioRepository;

@Service
public class CargoAtribuidoFuncionarioService {

	@Autowired
	private CargoAtribuidoFuncionarioRepository cargoAtribuidoFuncionarioRepository;
	@Autowired
	private DepartamentoAlocaFuncionarioRepository departamentoAlocaFuncionarioRepository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	private static final String URL_ATOA = "http://localhost:8070/funcionarios/";

	public CargoAtribuidoFuncionario find(Long idfuncionario) throws Exception {
		CargoAtribuidoFuncionario caf = this.cargoAtribuidoFuncionarioRepository.find(idfuncionario);
		if (caf == null) {
			caf = new CargoAtribuidoFuncionario();
		}
		return caf;
	}

	public List<CargoAtribuidoFuncionario> findAll() throws Exception {
		List<CargoAtribuidoFuncionario> list = this.cargoAtribuidoFuncionarioRepository.findAll();
		return list;
	}

	public CargoAtribuidoFuncionario findOne(CargoAtribuidoFuncionarioPK id) throws Exception {
		return this.cargoAtribuidoFuncionarioRepository.findOne(id);
	}

	public CargoAtribuidoFuncionario insert(CargoAtribuidoFuncionario caf) throws Exception {
		CargoAtribuidoFuncionario cargoAnterior = this.find(caf.getDepartamentoAlocaFuncionario().getFuncionario().getId());
		
		RestTemplate restTemplate = new RestTemplate();
		JSONObject json = new JSONObject();
		json.put("iddepartamento", caf.getDepartamentoAlocaFuncionario().getDepartamento().getId());
		json.put("idfuncionario", caf.getDepartamentoAlocaFuncionario().getFuncionario().getId());
		json.put("idcargo", caf.getCargo().getId());
		if ("S".equals(caf.getCargo().getGerente())) {
			caf.getDepartamentoAlocaFuncionario().getFuncionario().setRole(RoleEnum.GERENTE);
		} else {
			caf.getDepartamentoAlocaFuncionario().getFuncionario().setRole(RoleEnum.FUNCIONARIO);
		}
		restTemplate.put(URL_ATOA, caf.getDepartamentoAlocaFuncionario().getFuncionario());
		restTemplate.put(URL_ATOA + "cargo-departamento", json.toString());
		this.funcionarioRepository.saveAndFlush(caf.getDepartamentoAlocaFuncionario().getFuncionario());

		Date today = new Date();
		if (cargoAnterior.getId() != null) {
			cargoAnterior.setDataDesatribuicao(today);
			cargoAnterior.getDepartamentoAlocaFuncionario().setDataDesalocacao(today);
			this.departamentoAlocaFuncionarioRepository.saveAndFlush(cargoAnterior.getDepartamentoAlocaFuncionario());
			this.cargoAtribuidoFuncionarioRepository.saveAndFlush(cargoAnterior);
		}
		
		caf.getId().setDataAlocacao(today);
		caf.getId().setDataAtribuicao(today);
		caf.getDepartamentoAlocaFuncionario().getId().setDataAlocacao(today);
		this.departamentoAlocaFuncionarioRepository.saveAndFlush(caf.getDepartamentoAlocaFuncionario());
		return this.cargoAtribuidoFuncionarioRepository.saveAndFlush(caf);
	}

	public void delete(CargoAtribuidoFuncionarioPK id) throws Exception {
		CargoAtribuidoFuncionario caf = this.cargoAtribuidoFuncionarioRepository.findOne(id);
		this.cargoAtribuidoFuncionarioRepository.delete(caf);
	}

}
