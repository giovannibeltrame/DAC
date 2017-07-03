package br.ufpr.dac.rhindo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufpr.dac.rhindo.entity.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	@Query("select new Funcionario(f.id, f.celular, f.cpf, f.email, f.nome, f.rg, '', f.cep, f.bairro, f.rua, f.numero, f.complemento, f.cidade) from Funcionario f")
	public List<Funcionario> find();

}