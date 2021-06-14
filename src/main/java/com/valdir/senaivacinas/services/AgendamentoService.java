package com.valdir.senaivacinas.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.senaivacinas.domain.Agendamento;
import com.valdir.senaivacinas.domain.UnidadeAtendimento;
import com.valdir.senaivacinas.domain.Usuario;
import com.valdir.senaivacinas.domain.dto.AgendamentoDTO;
import com.valdir.senaivacinas.repositories.AgendamentoRepository;
import com.valdir.senaivacinas.repositories.UnidadeAtendimentoRepository;
import com.valdir.senaivacinas.repositories.UsuarioRepository;
import com.valdir.senaivacinas.services.exceptions.DataIntegrityViolationException;
import com.valdir.senaivacinas.services.exceptions.ObjectNotFoundException;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository repository;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UnidadeAtendimentoRepository unidadeRepository;
	@Autowired
	private UnidadeAtendimentoService unidadeService;

	/*
	 * Busca um agendamento por ID
	 */
	public AgendamentoDTO findById(Integer id) {
		Optional<Agendamento> obj = repository.findById(id);
		return new AgendamentoDTO(obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não contrado! Id: " + id + ", Tipo: " + Agendamento.class.getName())));
	}

	/*
	 * Lista todos os agendamentos
	 */
	public List<AgendamentoDTO> findAll() {
		List<Agendamento> list = repository.findAll();
		return list.stream().map(obj -> new AgendamentoDTO(obj)).collect(Collectors.toList());
	}

	/*
	 * Cria um agendamento
	 */
	public AgendamentoDTO create(AgendamentoDTO obj) {
		verificaDataAgendamento(obj.getData());
		return new AgendamentoDTO(updateData(obj));
	}

	/*
	 * Metodo criado para salvar as informações do novo agendamento
	 */
	private Agendamento updateData(AgendamentoDTO obj) {
		obj.setId(null);
		Usuario user = usuarioService.findById(obj.getUsuario().getId());
		System.out.println(user.getNome());
		UnidadeAtendimento unidadeAtendimento = unidadeService.findById(obj.getUnidadeAtendimento().getId());
		Agendamento newObj = AgendamentoDTO.toModel(obj);

		newObj.setUsuario(user);
		newObj.setUnidadeAtendimento(unidadeAtendimento);

		/*
		 * Verifica a quantidade de agendamento do usuário e se for de tamanho dois o
		 * mesmo não pode mais realizar agendamento
		 */
		if (user.getAgendamentos().size() < 2) {
			user.getAgendamentos().add(newObj);
		} else {
			throw new DataIntegrityViolationException(
					"Usuário já possui dois agendamento e não pode realizar mais agendamentos!");
		}

		unidadeAtendimento.getAgendamentos().add(newObj);
		usuarioRepository.save(user);
		unidadeRepository.save(unidadeAtendimento);
		return repository.save(newObj);
	}

	/*
	 * Metodo criado para validar se o usuário tentou realizar um agendamento com
	 * data posterior a data atual
	 */
	private void verificaDataAgendamento(Date agendamento) {
		Date now = new Date();
		if (agendamento.before(now)) {
			throw new DataIntegrityViolationException("A data do agendamento deve ser posterior a data atual!");
		}
	}

}
