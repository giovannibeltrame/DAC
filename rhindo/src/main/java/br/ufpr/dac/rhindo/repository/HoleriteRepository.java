package br.ufpr.dac.rhindo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.dac.rhindo.entity.Holerite;

@Repository
public interface HoleriteRepository extends JpaRepository<Holerite, Long> {

}
