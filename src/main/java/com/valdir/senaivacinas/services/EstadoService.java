package com.valdir.senaivacinas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.senaivacinas.domain.Estado;
import com.valdir.senaivacinas.domain.dto.EstadoDTO;
import com.valdir.senaivacinas.repositories.EstadoRepository;
import com.valdir.senaivacinas.services.exceptions.DataIntegrityViolationException;
import com.valdir.senaivacinas.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;

	/*
	 * Busca de um Estado por ID
	 */
	public Estado findById(Integer id) {
		Optional<Estado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não contrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
	}

	/*
	 * Busca de todos os estados na base de dados
	 */
	public List<Estado> findAll() {
		return repository.findAll();
	}

	/*
	 * Criando um novo Estado
	 */
	public EstadoDTO create(EstadoDTO objDTO) {
		try {
			Estado newObj = repository.save(new Estado(null, objDTO.getNome(), objDTO.getPais()));
			return new EstadoDTO(newObj);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("O sistema já possui um Estado com o nome: " + objDTO.getNome());
		}

	}

	/*
	 * Deletando Estado por ID
	 */
	public void delete(Integer id) {
		Estado obj = findById(id);
		try {
			repository.deleteById(id);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(
					obj.getNome() + " possui cidades associadas e não pode ser deletado! Id: " + id);
		}
	}
}
