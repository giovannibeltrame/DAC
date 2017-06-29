package br.ufpr.dac.rhindo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the departamento database table.
 * 
 */
@Entity
@Table(name="departamento")
@NamedQuery(name="Departamento.findAll", query="SELECT d FROM Departamento d")
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_departamento", sequenceName="seq_departamento", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_departamento")
	@Column(name="iddepartamento", unique=true, nullable=false)
	private Long id;

	@Column(length=255)
	private String localizacao;

	@Column(length=255)
	private String nome;

	@Column(length=1)
	private String situacao;

	//bi-directional many-to-one association to DepartamentoAlocaFuncionario
	@OneToMany(mappedBy="departamento")
	private List<DepartamentoAlocaFuncionario> departamentoAlocaFuncionarios;

	public Departamento() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocalizacao() {
		return this.localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
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

	public List<DepartamentoAlocaFuncionario> getDepartamentoAlocaFuncionarios() {
		return this.departamentoAlocaFuncionarios;
	}

	public void setDepartamentoAlocaFuncionarios(List<DepartamentoAlocaFuncionario> departamentoAlocaFuncionarios) {
		this.departamentoAlocaFuncionarios = departamentoAlocaFuncionarios;
	}

	public DepartamentoAlocaFuncionario addDepartamentoalocafuncionario(DepartamentoAlocaFuncionario departamentoAlocaFuncionario) {
		getDepartamentoAlocaFuncionarios().add(departamentoAlocaFuncionario);
		departamentoAlocaFuncionario.setDepartamento(this);

		return departamentoAlocaFuncionario;
	}

	public DepartamentoAlocaFuncionario removeDepartamentoalocafuncionario(DepartamentoAlocaFuncionario departamentoAlocaFuncionario) {
		getDepartamentoAlocaFuncionarios().remove(departamentoAlocaFuncionario);
		departamentoAlocaFuncionario.setDepartamento(null);

		return departamentoAlocaFuncionario;
	}

}