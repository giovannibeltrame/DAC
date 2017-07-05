package br.ufpr.dac.rhindo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the cargoatribuidofuncionario database table.
 * 
 */
@Embeddable
public class CargoAtribuidoFuncionarioPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "idcargo", insertable = false, updatable = false, unique = true, nullable = false)
	private Long idCargo;

	@Column(name = "iddepartamento", insertable = false, updatable = false, unique = true, nullable = false)
	private Long idDepartamento;

	@Column(name = "idfuncionario", insertable = false, updatable = false, unique = true, nullable = false)
	private Long idFuncionario;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataalocacao", insertable = false, updatable = false, unique = true, nullable = false)
	private Date dataAlocacao;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dataatribuicao", insertable = false, updatable = false, unique = true, nullable = false)
	private Date dataAtribuicao;

	public CargoAtribuidoFuncionarioPK() {
	}

	public Long getIdCargo() {
		return this.idCargo;
	}

	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}

	public Long getIdDepartamento() {
		return this.idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public Long getIdFuncionario() {
		return this.idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
	public Date getDataAtribuicao() {
		return this.dataAtribuicao;
	}

	public void setDataAtribuicao(Date dataAtribuicao) {
		this.dataAtribuicao = dataAtribuicao;
	}

	public Date getDataAlocacao() {
		return this.dataAtribuicao;
	}

	public void setDataAlocacao(Date dataAtribuicao) {
		this.dataAtribuicao = dataAtribuicao;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CargoAtribuidoFuncionarioPK)) {
			return false;
		}
		CargoAtribuidoFuncionarioPK castOther = (CargoAtribuidoFuncionarioPK) other;
		return this.idCargo.equals(castOther.idCargo) && this.idDepartamento.equals(castOther.idDepartamento)
				&& this.idFuncionario.equals(castOther.idFuncionario)
				&& this.dataAlocacao.equals(castOther.dataAlocacao);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idCargo.hashCode();
		hash = hash * prime + this.idDepartamento.hashCode();
		hash = hash * prime + this.idFuncionario.hashCode();
		hash = hash * prime + this.dataAlocacao.hashCode();

		return hash;
	}
}