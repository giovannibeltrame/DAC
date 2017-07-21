package br.ufpr.dac.rhindo.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the holerite database table.
 * 
 */
@Embeddable
public class HoleritePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="idfuncionario", insertable=false, updatable=false, unique=true, nullable=false)
	private Long idFuncionario;

	@Column(unique=true, nullable=false)
	private Integer mes;

	@Column(unique=true, nullable=false)
	private Integer ano;

	public HoleritePK() {
	}
	public Long getIdFuncionario() {
		return this.idFuncionario;
	}
	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public Integer getMes() {
		return this.mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Integer getAno() {
		return this.ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HoleritePK)) {
			return false;
		}
		HoleritePK castOther = (HoleritePK)other;
		return 
			this.idFuncionario.equals(castOther.idFuncionario)
			&& this.mes.equals(castOther.mes)
			&& this.ano.equals(castOther.ano);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idFuncionario.hashCode();
		hash = hash * prime + this.mes.hashCode();
		hash = hash * prime + this.ano.hashCode();
		
		return hash;
	}
}