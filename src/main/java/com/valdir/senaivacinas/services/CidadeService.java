package com.valdir.senaivacinas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.senaivacinas.domain.Cidade;
import com.valdir.senaivacinas.domain.dto.CidadeDTO;
import com.valdir.senaivacinas.repositories.CidadeRepository;
import com.valdir.senaivacinas.services.exceptions.DataIntegrityViolationException;
import com.valdir.senaivacinas.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	/*
	 * Busca de um Cidade por ID
	 */
	public Cidade findById(Integer id) {
		Optional<Cidade> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não contrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}

	/*
	 * Busca de todas as cidades na base de dados
	 */
	public List<Cidade> findAll() {
		return repository.findAll();
	}

	/*
	 * Criando uma nova Cidade
	 */
	public CidadeDTO create(CidadeDTO objDTO) {
		try {
			Cidade newObj = repository.save(new Cidade(null, objDTO.getNome(), objDTO.getEstado()));
			return new CidadeDTO(newObj);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("O sistema já possui uma Cidade com o nome: " + objDTO.getNome());
		}

	}

	/*
	 * Deletando Cidade por ID
	 */
	public void delete(Integer id) {
		Cidade obj = findById(id);
		try {
			repository.deleteById(id);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(
					obj.getNome() + " possui enderecços associados e não pode ser deletada! Id: " + id);
		}
	}
}
