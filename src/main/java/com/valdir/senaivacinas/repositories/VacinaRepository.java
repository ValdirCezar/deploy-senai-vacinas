package com.valdir.senaivacinas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valdir.senaivacinas.domain.Vacina;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, Integer> {

}
