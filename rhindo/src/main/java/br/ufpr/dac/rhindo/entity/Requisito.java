package br.ufpr.dac.rhindo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the requisito database table.
 * 
 */
@Entity
@Table(name="requisito")
@NamedQuery(name="Requisito.findAll", query="SELECT r FROM Requisito r")
public class Requisito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_requisito", sequenceName="seq_requisito", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_requisito")
	@Column(name="idrequisito", unique=true, nullable=false)
	private Long id;

	@Column(length=255)
	private String descricao;

	//bi-directional many-to-many association to Cargo
	@ManyToMany
	@JoinTable(
		name="requisitocargo"
		, joinColumns={
			@JoinColumn(name="idrequisito", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="idcargo", nullable=false)
			}
		)
	private List<Cargo> cargos;

	public Requisito() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Cargo> getCargos() {
		return this.cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

}