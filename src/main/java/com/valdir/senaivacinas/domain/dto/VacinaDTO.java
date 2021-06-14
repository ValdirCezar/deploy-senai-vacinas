package com.valdir.senaivacinas.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.valdir.senaivacinas.domain.UnidadeAtendimento;
import com.valdir.senaivacinas.domain.Vacina;

public class VacinaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Campo UNIDADE DE ATENDIMENTO é mandatório")
	private UnidadeAtendimento unidadeAtendimento;

	public VacinaDTO() {
		super();
	}

	public VacinaDTO(Vacina obj) {
		super();
		this.id = obj.getId();
		this.unidadeAtendimento = obj.getUnidadeAtendimento();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UnidadeAtendimento getUnidadeAtendimento() {
		return unidadeAtendimento;
	}

	public void setUnidadeAtendimento(UnidadeAtendimento unidadeAtendimento) {
		this.unidadeAtendimento = unidadeAtendimento;
	}

}
