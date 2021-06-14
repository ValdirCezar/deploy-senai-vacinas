package com.valdir.senaivacinas.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.valdir.senaivacinas.domain.Estado;
import com.valdir.senaivacinas.domain.Pais;

public class EstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Campo NOME é mandatório")
	private String nome;

	private Pais pais;

	public EstadoDTO() {
		super();
	}

	public EstadoDTO(Estado obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.pais = obj.getPais();
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

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

}
