package br.ufpr.dac.rhindo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.dac.rhindo.entity.CargoAtribuidoFuncionario;
import br.ufpr.dac.rhindo.entity.CargoAtribuidoFuncionarioPK;

@Repository
public interface CargoAtribuidoFuncionarioRepository extends JpaRepository<CargoAtribuidoFuncionario, CargoAtribuidoFuncionarioPK> {

}
