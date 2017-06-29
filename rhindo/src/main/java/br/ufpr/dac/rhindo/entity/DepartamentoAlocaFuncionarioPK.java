package br.ufpr.dac.rhindo.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the departamentoalocafuncionario database table.
 * 
 */
@Embeddable
public class DepartamentoAlocaFuncionarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Long iddepartamento;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Long idfuncionario;

	@Temporal(TemporalType.DATE)
	@Column(name="dataalocacao", unique=true, nullable=false)
	private java.util.Date dataAlocacao;

	public DepartamentoAlocaFuncionarioPK() {
	}
	public Long getIddepartamento() {
		return this.iddepartamento;
	}
	public void setIddepartamento(Long iddepartamento) {
		this.iddepartamento = iddepartamento;
	}
	public Long getIdfuncionario() {
		return this.idfuncionario;
	}
	public void setIdfuncionario(Long idfuncionario) {
		this.idfuncionario = idfuncionario;
	}
	public java.util.Date getDataAlocacao() {
		return this.dataAlocacao;
	}
	public void setDataAlocacao(java.util.Date dataAlocacao) {
		this.dataAlocacao = dataAlocacao;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DepartamentoAlocaFuncionarioPK)) {
			return false;
		}
		DepartamentoAlocaFuncionarioPK castOther = (DepartamentoAlocaFuncionarioPK)other;
		return 
			this.iddepartamento.equals(castOther.iddepartamento)
			&& this.idfuncionario.equals(castOther.idfuncionario)
			&& this.dataAlocacao.equals(castOther.dataAlocacao);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.iddepartamento.hashCode();
		hash = hash * prime + this.idfuncionario.hashCode();
		hash = hash * prime + this.dataAlocacao.hashCode();
		
		return hash;
	}
}