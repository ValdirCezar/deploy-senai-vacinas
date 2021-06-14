package com.valdir.senaivacinas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.senaivacinas.domain.Pais;
import com.valdir.senaivacinas.domain.dto.PaisDTO;
import com.valdir.senaivacinas.repositories.PaisRepository;
import com.valdir.senaivacinas.services.exceptions.DataIntegrityViolationException;
import com.valdir.senaivacinas.services.exceptions.ObjectNotFoundException;

@Service
public class PaisService {

	@Autowired
	private PaisRepository repository;

	/*
	 * Busca de um Pais por ID
	 */
	public Pais findById(Integer id) {
		Optional<Pais> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não contrado! Id: " + id + ", Tipo: " + Pais.class.getName()));
	}

	/*
	 * Busca de todos os paises na base de dados
	 */
	public List<Pais> findAll() {
		return repository.findAll();
	}

	/*
	 * Criando um novo Pais
	 */
	public PaisDTO create(PaisDTO objDTO) {
		try {
			Pais newObj = repository.save(new Pais(null, objDTO.getNome()));
			return new PaisDTO(newObj);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("O sistema já possui um Pais com o nome: " + objDTO.getNome());
		}

	}

	/*
	 * Deletando Pais por ID
	 */
	public void delete(Integer id) {
		Pais obj = findById(id);
		try {
			repository.deleteById(id);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(
					obj.getNome() + " possui estados associados e não pode ser deletado! Id: " + id);
		}
	}
}
