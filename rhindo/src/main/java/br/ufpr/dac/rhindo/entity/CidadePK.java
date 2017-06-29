package br.ufpr.dac.rhindo.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the cidade database table.
 * 
 */
@Embeddable
public class CidadePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(name="seq_cidade", sequenceName="seq_cidade", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_cidade")
	@Column(name="idcidade", unique=true, nullable=false)
	private Long id;

	@Column(name="idunidadefederativa", insertable=false, updatable=false, unique=true, nullable=false)
	private Long idUnidadeFederativa;

	public CidadePK() {
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdUnidadeFederativa() {
		return this.idUnidadeFederativa;
	}
	public void setIdUnidadeFederativa(Long idUnidadeFederativa) {
		this.idUnidadeFederativa = idUnidadeFederativa;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CidadePK)) {
			return false;
		}
		CidadePK castOther = (CidadePK)other;
		return 
			this.id.equals(castOther.id)
			&& this.idUnidadeFederativa.equals(castOther.idUnidadeFederativa);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.idUnidadeFederativa.hashCode();
		
		return hash;
	}
}