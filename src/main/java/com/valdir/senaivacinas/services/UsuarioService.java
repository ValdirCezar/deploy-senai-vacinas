package com.valdir.senaivacinas.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.valdir.senaivacinas.domain.Usuario;
import com.valdir.senaivacinas.domain.dto.UsuarioDTO;
import com.valdir.senaivacinas.repositories.EnderecoRepository;
import com.valdir.senaivacinas.repositories.UsuarioRepository;
import com.valdir.senaivacinas.services.exceptions.DataIntegrityViolationException;
import com.valdir.senaivacinas.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	/*
	 * Busca de um Usuário por ID
	 */
	public Usuario findById(Integer id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não contrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
	/*
	 * Busca de um Usuário por E-MAIL
	 */
	public Usuario findByEmail(String email) {
		Usuario obj = repository.findByEmail(email);
		if(obj.equals(null)) {
			throw new ObjectNotFoundException(
					"Objeto não contrado! Id: " + email + ", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}

	/*
	 * Busca de todos os Usuários
	 */
	public List<UsuarioDTO> findAll() {
		List<Usuario> list = repository.findAll();
		List<UsuarioDTO> listDTO = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return listDTO;
	}

	/*
	 * Criando um Usuário
	 */
	public @Valid UsuarioDTO create(@Valid UsuarioDTO obj) {
		verificaDados(obj);
		Usuario newObj = UsuarioDTO.toModel(obj);
		newObj.setSenha(encoder.encode(obj.getSenha()));
		newObj = repository.save(newObj);
		enderecoRepository.save(newObj.getEndereco());
		return new UsuarioDTO(newObj);
	}

	/*
	 * Atualizando um Usuário
	 */
	public @Valid UsuarioDTO update(@Valid UsuarioDTO obj, Integer id) {
		obj.setId(id);
		verificaDados(obj);
		Usuario objUp = findById(id);
		objUp = repository.save(UsuarioDTO.toModel(obj));
		return new UsuarioDTO(objUp);
	}

	/*
	 * Metodo que irá verificar se existem contas com o e-mail, telefone ou CPF que
	 * o usuário passou na criação de sua conta. Caso exista é lançada uma exceção
	 * personalizada para o usuário e o erro não ocorre a nível de banco de dados
	 */
	private void verificaDados(UsuarioDTO obj) {
		Usuario user = repository.findByCpf(obj.getCpf());
		if (user != null && (obj.getId() != user.getId())) {
			throw new DataIntegrityViolationException("CPF " + obj.getCpf() + " já possui cadastro no sistema!");
		}

		user = repository.findByTelefone(obj.getTelefone());
		if (user != null && (obj.getId() != user.getId())) {
			throw new DataIntegrityViolationException(
					"Telefone " + obj.getTelefone() + " já possui cadastro no sistema!");
		}

		user = repository.findByEmail(obj.getEmail());
		if (user != null && (obj.getId() != user.getId())) {
			throw new DataIntegrityViolationException("E-mail " + obj.getEmail() + " já possui cadastro no sistema!");
		}

	}

}
