package com.valdir.senaivacinas.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.valdir.senaivacinas.domain.Agendamento;
import com.valdir.senaivacinas.domain.Endereco;
import com.valdir.senaivacinas.domain.UnidadeAtendimento;
import com.valdir.senaivacinas.domain.Vacina;

public class UnidadeAtendimentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Campo NOME é mandatório")
	private String nome;
	private Integer vacinados;
	private List<Vacina> vacinas = new ArrayList<>();
	
	@JsonIgnore
	private List<Agendamento> agendamentos = new ArrayList<>();

	@NotEmpty(message = "Campo ENDERECO é mandatório")
	private Endereco endereco;

	public UnidadeAtendimentoDTO() {
		super();
	}

	public UnidadeAtendimentoDTO(UnidadeAtendimento obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.vacinados = obj.getVacinados();
		this.vacinas = obj.getVacinas();
		this.agendamentos = obj.getAgendamentos();
		this.endereco = obj.getEndereco();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getVacinados() {
		return vacinados;
	}

	public void setVacinados(Integer vacinados) {
		this.vacinados = vacinados;
	}

	public List<Vacina> getVacinas() {
		return vacinas;
	}

	public void setVacinas(List<Vacina> vacinas) {
		this.vacinas = vacinas;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
