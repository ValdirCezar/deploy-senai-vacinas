package com.valdir.senaivacinas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.senaivacinas.domain.UnidadeAtendimento;
import com.valdir.senaivacinas.repositories.UnidadeAtendimentoRepository;
import com.valdir.senaivacinas.services.exceptions.ObjectNotFoundException;

@Service
public class UnidadeAtendimentoService {

	@Autowired
	private UnidadeAtendimentoRepository repository;

	public UnidadeAtendimento findById(Integer id) {
		Optional<UnidadeAtendimento> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não contrado! Id: " + id + ", Tipo: " + UnidadeAtendimento.class.getName()));
	}
}
