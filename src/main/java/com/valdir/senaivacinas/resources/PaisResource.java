package com.valdir.senaivacinas.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.valdir.senaivacinas.domain.Pais;
import com.valdir.senaivacinas.domain.dto.PaisDTO;
import com.valdir.senaivacinas.services.PaisService;

@RestController
@RequestMapping(value = "/paises")
public class PaisResource {

	@Autowired
	private PaisService service;

	/*
	 * Busca de Pais por ID
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<PaisDTO> findById(@PathVariable Integer id) {
		Pais obj = service.findById(id);
		return ResponseEntity.ok().body(new PaisDTO(obj));
	}

	/*
	 * Listando todos os paises
	 */
	@GetMapping
	public ResponseEntity<List<PaisDTO>> findAll() {
		List<Pais> list = service.findAll();
		List<PaisDTO> listDTO = list.stream().map(obj -> new PaisDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	/*
	 * Criando um novo Pais
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PaisDTO> create(@Valid @RequestBody PaisDTO obj) {
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	/*
	 * Deletar Pais por ID
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
