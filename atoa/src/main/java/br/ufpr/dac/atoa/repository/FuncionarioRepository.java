package br.ufpr.dac.atoa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufpr.dac.atoa.entity.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	@Query("select new Funcionario(f.id, f.email, f.nome, '') from Funcionario f")
	public List<Funcionario> find();
	
	@Query("select new Funcionario(f.email, f.senha, f.role) from Funcionario f where f.email = :email and f.senha = :senha")
	public Funcionario loadProfile(@Param("email") String email, @Param("senha") String senha);

}