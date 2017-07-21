package br.ufpr.dac.rhindo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the cargoatribuidofuncionario database table.
 * 
 */
@Entity
@Table(name="cargoatribuidofuncionario")
@NamedQuery(name="CargoAtribuidoFuncionario.findAll", query="SELECT c FROM CargoAtribuidoFuncionario c")
public class CargoAtribuidoFuncionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CargoAtribuidoFuncionarioPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="datadesatribuicao")
	private Date dataDesatribuicao;

	//bi-directional many-to-one association to Cargo
	@ManyToOne
	@JoinColumn(name="idcargo", nullable=false, insertable=false, updatable=false)
	private Cargo cargo;

	//bi-directional many-to-one association to DepartamentoAlocaFuncionario
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="dataalocacao", referencedColumnName="dataalocacao", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="iddepartamento", referencedColumnName="iddepartamento", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="idfuncionario", referencedColumnName="idfuncionario", nullable=false, insertable=false, updatable=false)
		})
	private DepartamentoAlocaFuncionario departamentoAlocaFuncionario;

	public CargoAtribuidoFuncionario() {
	}

	public CargoAtribuidoFuncionarioPK getId() {
		return this.id;
	}

	public void setId(CargoAtribuidoFuncionarioPK id) {
		this.id = id;
	}

	public Date getDataDesatribuicao() {
		return this.dataDesatribuicao;
	}

	public void setDataDesatribuicao(Date dataDesatribuicao) {
		this.dataDesatribuicao = dataDesatribuicao;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public DepartamentoAlocaFuncionario getDepartamentoAlocaFuncionario() {
		return this.departamentoAlocaFuncionario;
	}

	public void setDepartamentoAlocaFuncionario(DepartamentoAlocaFuncionario departamentoAlocaFuncionario) {
		this.departamentoAlocaFuncionario = departamentoAlocaFuncionario;
	}

}