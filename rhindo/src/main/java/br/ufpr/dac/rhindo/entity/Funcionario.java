package br.ufpr.dac.rhindo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the funcionario database table.
 * 
 */
@Entity
@Table(name = "funcionario")
@NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f")
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idfuncionario", unique = true, nullable = false)
	private Long id;

	@Column(length = 16)
	private String celular;

	@Column(length = 16)
	private String cpf;

	@Column(length = 64)
	private String email;

	@Column(length = 255)
	private String nome;

	@Column(length = 16)
	private String rg;

	@Column(length = 255)
	private String senha;

	@Column(length = 16)
	private String cep;

	@Column(length = 64)
	private String bairro;

	@Column(length = 64)
	private String rua;

	@Column(length = 8)
	private String numero;

	@Column(length = 32)
	private String complemento;

	// //bi-directional many-to-one association to DepartamentoAlocaFuncionario
	// @OneToMany(mappedBy="funcionario")
	// private List<DepartamentoAlocaFuncionario> departamentoAlocaFuncionarios;
	//
	// //bi-directional many-to-one association to Holerite
	// @OneToMany(mappedBy="funcionario")
	// private List<Holerite> holerites;

	// //bi-directional many-to-one association to UnidadeFederativa
	// @ManyToOne()
	// @JoinColumn(name="idunidadefederativarg")
	// private UnidadeFederativa unidadeFederativaRG;
	//
	// //bi-directional many-to-one association to UnidadeFederativa
	// @ManyToOne()
	// @JoinColumn(name="idunidadefederativareside")
	// private UnidadeFederativa unidadeFederativa;

	// bi-directional many-to-one association to Cidade
	@ManyToOne()
	@JoinColumn(name = "idcidade")
	private Cidade cidade;

	public Funcionario() {
	}

	public Funcionario(Long id, String celular, String cpf, String email, String nome, String rg, String senha,
			String cep, String bairro, String rua, String numero, String complemento,
			/*
			 * UnidadeFederativa unidadeFederativa, UnidadeFederativa
			 * unidadeFederativaRG,
			 */ Cidade cidade) {
		super();
		this.id = id;
		this.celular = celular;
		this.cpf = cpf;
		this.email = email;
		this.nome = nome;
		this.rg = rg;
		this.senha = senha;
		this.cep = cep;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		// this.unidadeFederativa = unidadeFederativa;
		// this.unidadeFederativaRG = unidadeFederativaRG;
		this.cidade = cidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
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

	// public List<DepartamentoAlocaFuncionario>
	// getDepartamentoAlocaFuncionarios() {
	// return departamentoAlocaFuncionarios;
	// }
	//
	// public void
	// setDepartamentoAlocaFuncionarios(List<DepartamentoAlocaFuncionario>
	// departamentoAlocaFuncionarios) {
	// this.departamentoAlocaFuncionarios = departamentoAlocaFuncionarios;
	// }
	//
	// public List<Holerite> getHolerites() {
	// return holerites;
	// }
	//
	// public void setHolerites(List<Holerite> holerites) {
	// this.holerites = holerites;
	// }
	//
	// public UnidadeFederativa getUnidadeFederativaRG() {
	// return unidadeFederativaRG;
	// }
	//
	// public void setUnidadeFederativaRG(UnidadeFederativa unidadeFederativaRG)
	// {
	// this.unidadeFederativaRG = unidadeFederativaRG;
	// }
	//
	// public UnidadeFederativa getUnidadeFederativa() {
	// return unidadeFederativa;
	// }
	//
	// public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
	// this.unidadeFederativa = unidadeFederativa;
	// }

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}