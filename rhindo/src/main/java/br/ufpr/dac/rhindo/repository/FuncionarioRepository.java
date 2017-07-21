package br.ufpr.dac.rhindo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufpr.dac.rhindo.entity.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	@Query("select new Funcionario(f.id, f.celular, f.cpf, f.email, f.nome, f.rg, f.senha, f.cep, f.bairro, f.rua, f.numero, f.complemento, f.cidade, f.role) from Funcionario f")
	public List<Funcionario> find();
	
	public Funcionario findByCpf(String cpf);
	
	public Funcionario findByRg(String rg);
	
	@Query("select new Funcionario(f.email, f.senha, f.role) from Funcionario f where f.email = :email and f.senha = :senha")
	public Funcionario loadProfile(@Param("email") String email, @Param("senha") String senha);

}