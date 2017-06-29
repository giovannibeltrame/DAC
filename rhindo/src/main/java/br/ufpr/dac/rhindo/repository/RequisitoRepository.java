package br.ufpr.dac.rhindo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.dac.rhindo.entity.Requisito;

@Repository
public interface RequisitoRepository extends JpaRepository<Requisito, Long> {

}
