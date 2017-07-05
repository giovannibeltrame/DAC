package br.ufpr.dac.rhindo.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.ufpr.dac.rhindo.entity.CargoAtribuidoFuncionario;
import br.ufpr.dac.rhindo.entity.CargoAtribuidoFuncionarioPK;
import br.ufpr.dac.rhindo.exception.ResourceNotFoundException;
import br.ufpr.dac.rhindo.repository.CargoAtribuidoFuncionarioRepository;

@Service
public class CargoAtribuidoFuncionarioService {

	@Autowired
	private CargoAtribuidoFuncionarioRepository cargoAtribuidoFuncionarioRepository;
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
		CargoAtribuidoFuncionario caf = this.cargoAtribuidoFuncionarioRepository.findOne(id);
		if (caf == null) {
			throw new ResourceNotFoundException();
		}
		return caf;
	}

	public CargoAtribuidoFuncionario insert(CargoAtribuidoFuncionario caf) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		JSONObject json = new JSONObject();
		json.put("idcargo", caf.getCargo().getId());
		json.put("idfuncionario", caf.getDepartamentoAlocaFuncionario().getFuncionario().getId());
		restTemplate.put(URL_ATOA + "cargo", json);
		return this.cargoAtribuidoFuncionarioRepository.saveAndFlush(caf);
	}

	public CargoAtribuidoFuncionario update(CargoAtribuidoFuncionario caf) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		JSONObject json = new JSONObject();
		json.put("idcargo", "");
		json.put("idfuncionario", caf.getDepartamentoAlocaFuncionario().getFuncionario().getId());
		restTemplate.put(URL_ATOA + "cargo", json);
		return this.cargoAtribuidoFuncionarioRepository.saveAndFlush(caf);
	}

	public void delete(CargoAtribuidoFuncionarioPK id) throws Exception {
		CargoAtribuidoFuncionario caf = this.cargoAtribuidoFuncionarioRepository.findOne(id);
		this.cargoAtribuidoFuncionarioRepository.delete(caf);
	}

}
