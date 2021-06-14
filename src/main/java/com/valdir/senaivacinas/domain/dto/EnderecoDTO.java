package com.valdir.senaivacinas.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.valdir.senaivacinas.domain.Cidade;
import com.valdir.senaivacinas.domain.Endereco;
import com.valdir.senaivacinas.domain.UnidadeAtendimento;
import com.valdir.senaivacinas.domain.Usuario;

public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Campo LOGRADOURO é mandatório")
	private String logradouro;

	@NotEmpty(message = "Campo NUMERO é mandatório")
	private String numero;
	private String complemento;

	@NotEmpty(message = "Campo BAIRRO é mandatório")
	private String bairro;

	@NotEmpty(message = "Campo CEP é mandatório")
	private String cep;

	@NotEmpty(message = "Campo USUARIO é mandatório")
	private Usuario usuario;

	@NotEmpty(message = "Campo CIDADE é mandatório")
	private Cidade cidade;

	private UnidadeAtendimento unidadeAtendimento;

	public EnderecoDTO() {
		super();
	}

	public EnderecoDTO(Endereco obj) {
		super();
		this.id = obj.getId();
		this.logradouro = obj.getLogradouro();
		this.numero = obj.getNumero();
		this.complemento = obj.getComplemento();
		this.bairro = obj.getBairro();
		this.cep = obj.getCep();
		this.usuario = obj.getUsuario();
		this.cidade = obj.getCidade();
		this.unidadeAtendimento = obj.getUnidadeAtendimento();
	}

	public static Endereco fromDTO(Endereco obj) {
		Endereco newObj = new Endereco(
				null, 
				obj.getLogradouro(), 
				obj.getNumero(), 
				obj.getComplemento(),
				obj.getBairro(), 
				obj.getCep(), 
				obj.getUsuario(), 
				obj.getCidade());
		return newObj;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public UnidadeAtendimento getUnidadeAtendimento() {
		return unidadeAtendimento;
	}

	public void setUnidadeAtendimento(UnidadeAtendimento unidadeAtendimento) {
		this.unidadeAtendimento = unidadeAtendimento;
	}

}
