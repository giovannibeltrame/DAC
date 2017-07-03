package br.ufpr.dac.atoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.dac.atoa.entity.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

}
