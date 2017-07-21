package br.ufpr.dac.rhindo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.dac.rhindo.entity.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
