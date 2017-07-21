package br.ufpr.dac.rhindo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the departamentoalocafuncionario database table.
 * 
 */
@Entity
@Table(name = "departamentoalocafuncionario")
@NamedQuery(name = "DepartamentoAlocaFuncionario.findAll", query = "SELECT d FROM DepartamentoAlocaFuncionario d")
public class DepartamentoAlocaFuncionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DepartamentoAlocaFuncionarioPK id;

	@Temporal(TemporalType.DATE)
	@Column(name = "datadesalocacao")
	private Date dataDesalocacao;

//	// bi-directional many-to-one association to CargoAtribuidoFuncionario
//	@OneToMany(mappedBy = "departamentoAlocaFuncionario")
//	private List<CargoAtribuidoFuncionario> cargoAtribuidoFuncionarios;

	// bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name = "iddepartamento", nullable = false, insertable = false, updatable = false)
	private Departamento departamento;

	// bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name = "idfuncionario", nullable = false, insertable = false, updatable = false)
	private Funcionario funcionario;

	public DepartamentoAlocaFuncionario() {
	}

	public DepartamentoAlocaFuncionarioPK getId() {
		return this.id;
	}

	public void setId(DepartamentoAlocaFuncionarioPK id) {
		this.id = id;
	}

	public Date getDataDesalocacao() {
		return this.dataDesalocacao;
	}

	public void setDataDesalocacao(Date dataDesalocacao) {
		this.dataDesalocacao = dataDesalocacao;
	}

//	public List<CargoAtribuidoFuncionario> getCargoAtribuidoFuncionarios() {
//		return this.cargoAtribuidoFuncionarios;
//	}
//
//	public void setCargoAtribuidoFuncionarios(List<CargoAtribuidoFuncionario> cargoAtribuidoFuncionarios) {
//		this.cargoAtribuidoFuncionarios = cargoAtribuidoFuncionarios;
//	}
//
//	public CargoAtribuidoFuncionario addCargoatribuidofuncionario(CargoAtribuidoFuncionario cargoAtribuidoFuncionario) {
//		getCargoAtribuidoFuncionarios().add(cargoAtribuidoFuncionario);
//		cargoAtribuidoFuncionario.setDepartamentoAlocaFuncionario(this);
//
//		return cargoAtribuidoFuncionario;
//	}
//
//	public CargoAtribuidoFuncionario removeCargoatribuidofuncionario(
//			CargoAtribuidoFuncionario cargoAtribuidoFuncionario) {
//		getCargoAtribuidoFuncionarios().remove(cargoAtribuidoFuncionario);
//		cargoAtribuidoFuncionario.setDepartamentoAlocaFuncionario(null);
//
//		return cargoAtribuidoFuncionario;
//	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}