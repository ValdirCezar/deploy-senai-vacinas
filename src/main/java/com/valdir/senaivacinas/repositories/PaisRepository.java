package com.valdir.senaivacinas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valdir.senaivacinas.domain.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {

}
