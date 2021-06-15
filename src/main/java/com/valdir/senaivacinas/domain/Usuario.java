package com.valdir.senaivacinas.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.valdir.senaivacinas.domain.enums.Perfil;

@Entity
@Table(name = "Usuarios")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String cpf;

	private String nome;
	private String sobrenome;
	private Double altura;
	private Double peso;
	private Character sexo;

	@Column(unique = true)
	private String telefone;

	private Date dataDeNascimento;
	private Integer idade;
	private Boolean obeso;
	private Boolean deficiente;
	private String tipoDeDeficiencia;

	@Column(unique = true)
	private String email;
	private String senha;

	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Endereco endereco;

	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Agendamento> agendamentos = new ArrayList<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	public Usuario() {
		super();
		addPerfil(Perfil.USUARIO);
	}

	public Usuario(Integer id, String cpf, String nome, String sobrenome, Double altura, Double peso, Character sexo,
			String telefone, Date dataDeNascimento, Boolean deficiente, String tipoDeDeficiencia, String email,
			String senha) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.altura = altura;
		this.peso = peso;
		this.sexo = sexo;
		this.telefone = telefone;
		this.dataDeNascimento = dataDeNascimento;
		this.deficiente = deficiente;
		this.tipoDeDeficiencia = tipoDeDeficiencia;
		this.email = email;
		this.senha = senha;
		this.verificaObesidade();
		this.calculaIdade();
		addPerfil(Perfil.USUARIO);
	}

	// Verifica se o usuario é obeso
	public void verificaObesidade() {
		this.setObeso((this.getPeso() / Math.pow(this.getAltura(), 2)) >= 30 ? true : false);
	}

	// Metodo que irá calcular a idade do usuario
	public void calculaIdade() {
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.setTime(this.getDataDeNascimento());
		Calendar hoje = Calendar.getInstance();

		this.setIdade(hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR));

		if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
			idade--;
		} else {
			if (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH)
					&& hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
				idade--;
			}
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Boolean getObeso() {
		return obeso;
	}

	public void setObeso(Boolean obeso) {
		this.obeso = obeso;
	}

	public Boolean getDeficiente() {
		return deficiente;
	}

	public void setDeficiente(Boolean deficiente) {
		this.deficiente = deficiente;
	}

	public String getTipoDeDeficiencia() {
		return tipoDeDeficiencia;
	}

	public void setTipoDeDeficiencia(String tipoDeDeficiencia) {
		this.tipoDeDeficiencia = tipoDeDeficiencia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public Set<Perfil> getPerfis() {
		return this.perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
