package com.valdir.senaivacinas.domain.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.valdir.senaivacinas.domain.Agendamento;

public class AgendamentoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date data;
	private Boolean finalizado;
	private String observacoes;

	private Integer usuario;
	private Integer unidadeAtendimento;

	public AgendamentoDTO() {
		super();
	}

	public AgendamentoDTO(Agendamento obj) {
		super();
		this.id = obj.getId();
		this.data = obj.getData();
		this.finalizado = obj.getFinalizado();
		this.observacoes = obj.getObservações();
		this.usuario = obj.getUsuario().getId();
		this.unidadeAtendimento = obj.getUnidadeAtendimento().getId();
	}

	public static Agendamento toModel(AgendamentoDTO obj) {
		Agendamento newObj = new Agendamento();
		newObj.setId(obj.getId());
		newObj.setData(obj.getData());
		newObj.setFinalizado(obj.getFinalizado());
		newObj.setObservações(obj.getObservações());
		return newObj;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public String getObservações() {
		return observacoes;
	}

	public void setObservações(String observações) {
		this.observacoes = observações;
	}

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	public Integer getUnidadeAtendimento() {
		return unidadeAtendimento;
	}

	public void setUnidadeAtendimento(Integer unidadeAtendimento) {
		this.unidadeAtendimento = unidadeAtendimento;
	}

}
