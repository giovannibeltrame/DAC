package br.ufpr.dac.rhindo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the unidadefederativa database table.
 * 
 */
@Entity
@Table(name="unidadefederativa")
@NamedQuery(name="UnidadeFederativa.findAll", query="SELECT u FROM UnidadeFederativa u")
public class UnidadeFederativa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_unidadefederativa", sequenceName="seq_unidadefederativa", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_unidadefederativa")
	@Column(name="idunidadefederativa", unique=true, nullable=false)
	private Long id;

	@Column(length=64)
	private String nome;

	@Column(length=1)
	private String situacao;

//	//bi-directional many-to-one association to Cidade
//	@OneToMany(mappedBy="unidadeFederativa")
//	private List<Cidade> cidades;
//
//	//bi-directional many-to-one association to Funcionario
//	@OneToMany(mappedBy="unidadeFederativaRG")
//	private List<Funcionario> funcionariosRG;
//
//	//bi-directional many-to-one association to Funcionario
//	@OneToMany(mappedBy="unidadeFederativa")
//	private List<Funcionario> funcionarios;

	public UnidadeFederativa() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

//	public List<Cidade> getCidades() {
//		return this.cidades;
//	}
//
//	public void setCidades(List<Cidade> cidades) {
//		this.cidades = cidades;
//	}
//
//	public Cidade addCidade(Cidade cidade) {
//		getCidades().add(cidade);
//		cidade.setUnidadeFederativa(this);
//
//		return cidade;
//	}
//
//	public Cidade removeCidade(Cidade cidade) {
//		getCidades().remove(cidade);
//		cidade.setUnidadeFederativa(null);
//
//		return cidade;
//	}
//
//	public List<Funcionario> getFuncionariosRG() {
//		return this.funcionariosRG;
//	}
//
//	public void setFuncionariosRG(List<Funcionario> funcionariosRG) {
//		this.funcionariosRG = funcionariosRG;
//	}
//
//	public Funcionario addFuncionariosRG(Funcionario funcionario) {
//		getFuncionariosRG().add(funcionario);
//		funcionario.setUnidadeFederativaRG(this);
//
//		return funcionario;
//	}
//
//	public Funcionario removeFuncionariosRG(Funcionario funcionario) {
//		getFuncionariosRG().remove(funcionario);
//		funcionario.setUnidadeFederativaRG(null);
//
//		return funcionario;
//	}
//
//	public List<Funcionario> getFuncionarios() {
//		return this.funcionarios;
//	}
//
//	public void setFuncionarios(List<Funcionario> funcionarios) {
//		this.funcionarios = funcionarios;
//	}
//
//	public Funcionario addFuncionarios(Funcionario funcionario) {
//		getFuncionarios().add(funcionario);
//		funcionario.setUnidadeFederativa(this);
//
//		return funcionario;
//	}
//
//	public Funcionario removeFuncionarios(Funcionario funcionario) {
//		getFuncionarios().remove(funcionario);
//		funcionario.setUnidadeFederativa(null);
//
//		return funcionario;
//	}

}