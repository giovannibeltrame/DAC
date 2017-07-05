package br.ufpr.dac.rhindo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufpr.dac.rhindo.entity.CargoAtribuidoFuncionario;
import br.ufpr.dac.rhindo.entity.CargoAtribuidoFuncionarioPK;

@Repository
public interface CargoAtribuidoFuncionarioRepository extends JpaRepository<CargoAtribuidoFuncionario, CargoAtribuidoFuncionarioPK> {

	@Query("SELECT c FROM CargoAtribuidoFuncionario c where c.departamentoAlocaFuncionario.funcionario.id = :idfuncionario and c.dataDesatribuicao is null and c.departamentoAlocaFuncionario.dataDesalocacao is null")
	public CargoAtribuidoFuncionario find(@Param("idfuncionario") Long idfuncionario);
}
