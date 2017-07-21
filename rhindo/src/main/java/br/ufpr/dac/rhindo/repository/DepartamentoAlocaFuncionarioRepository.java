package br.ufpr.dac.rhindo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.dac.rhindo.entity.DepartamentoAlocaFuncionario;
import br.ufpr.dac.rhindo.entity.DepartamentoAlocaFuncionarioPK;

@Repository
public interface DepartamentoAlocaFuncionarioRepository extends JpaRepository<DepartamentoAlocaFuncionario, DepartamentoAlocaFuncionarioPK> {

}
