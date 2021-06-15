package com.valdir.senaivacinas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Agendamentos")
public class Agendamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date data;
	private Boolean finalizado;
	private String observacoes;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "unidade_de_atendimento_id")
	private UnidadeAtendimento unidadeAtendimento;

	public Agendamento() {
		super();
	}

	public Agendamento(Integer id, Date data, Boolean finalizado, String observacoes, Usuario usuario,
			UnidadeAtendimento unidadeAtendimento) {
		super();
		this.id = id;
		this.data = data;
		this.finalizado = finalizado;
		this.observacoes = observacoes;
		this.usuario = usuario;
		this.unidadeAtendimento = unidadeAtendimento;
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

	public void setObservações(String observacoes) {
		this.observacoes = observacoes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UnidadeAtendimento getUnidadeAtendimento() {
		return unidadeAtendimento;
	}

	public void setUnidadeAtendimento(UnidadeAtendimento unidadeAtendimento) {
		this.unidadeAtendimento = unidadeAtendimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agendamento other = (Agendamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
