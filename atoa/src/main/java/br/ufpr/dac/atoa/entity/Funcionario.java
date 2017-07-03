package br.ufpr.dac.atoa.entity;

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
@Table(name="funcionario")
@NamedQuery(name="Funcionario.findAll", query="SELECT f FROM Funcionario f")
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idfuncionario", unique=true, nullable=false)
	private Long id;

	@Column(length=64)
	private String email;

	@Column(length=255)
	private String nome;

	@Column(length=255)
	private String senha;

//	//bi-directional many-to-one association to Atividade
//	@OneToMany(mappedBy="funcionario")
//	private List<Atividade> atividades;

	//bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name="iddepartamento")
	private Departamento departamento;
	
	//bi-directional many-to-one association to Cargo
	@ManyToOne
	@JoinColumn(name="idcargo")
	private Cargo cargo;

	public Funcionario() {
	}
	
	public Funcionario(Long id, String email, String nome, String senha) {
		super();
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.senha = senha;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

//	public List<Atividade> getAtividades() {
//		return this.atividades;
//	}
//
//	public void setAtividades(List<Atividade> atividades) {
//		this.atividades = atividades;
//	}
//
//	public Atividade addAtividade(Atividade atividade) {
//		getAtividades().add(atividade);
//		atividade.setFuncionario(this);
//
//		return atividade;
//	}
//
//	public Atividade removeAtividade(Atividade atividade) {
//		getAtividades().remove(atividade);
//		atividade.setFuncionario(null);
//
//		return atividade;
//	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

}