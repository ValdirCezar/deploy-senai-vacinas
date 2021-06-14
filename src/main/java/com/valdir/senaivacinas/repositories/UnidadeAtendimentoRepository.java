package com.valdir.senaivacinas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valdir.senaivacinas.domain.UnidadeAtendimento;

@Repository
public interface UnidadeAtendimentoRepository extends JpaRepository<UnidadeAtendimento, Integer> {

}
