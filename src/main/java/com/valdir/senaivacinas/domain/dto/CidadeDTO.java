package com.valdir.senaivacinas.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.valdir.senaivacinas.domain.Cidade;
import com.valdir.senaivacinas.domain.Estado;

public class CidadeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Campo NOME é mandatório")
	private String nome;

	private Estado estado;

	public CidadeDTO() {
		super();
	}

	public CidadeDTO(Cidade obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.estado = obj.getEstado();
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
