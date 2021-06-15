package com.valdir.senaivacinas.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.valdir.senaivacinas.domain.dto.UsuarioDTO;
import com.valdir.senaivacinas.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;

	/*
	 * Busca de um Usuário por ID
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id) {
		UsuarioDTO obj = new UsuarioDTO(service.findById(id));
		return ResponseEntity.ok().body(obj);
	}
	
	/*
	 * Busca de um Usuário por E-MAIL
	 */
	@GetMapping(value = "/findByEmail")
	public ResponseEntity<UsuarioDTO> findByEmail(@RequestParam String email) {
		UsuarioDTO obj = new UsuarioDTO(service.findByEmail(email));
		return ResponseEntity.ok().body(obj);
	}

	/*
	 * Busca de todos os Usuários
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<UsuarioDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	/*
	 * Criando um Usuário
	 */
	@PostMapping
	public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO obj) {
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	/*
	 * Atualizando um Usuário
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id, @Valid @RequestBody UsuarioDTO obj) {
		obj = service.update(obj, id);
		return ResponseEntity.ok().body(obj);
	}
}
