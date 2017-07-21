package br.ufpr.dac.atoa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the tipoatividade database table.
 * 
 */
@Entity
@Table(name="tipoatividade")
@NamedQuery(name="TipoAtividade.findAll", query="SELECT t FROM TipoAtividade t")
public class TipoAtividade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_tipoatividade", sequenceName="seq_tipoatividade", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_tipoatividade")
	@Column(name="idtipoatividade", unique=true, nullable=false)
	private Long id;

	@Column(length=255)
	private String nome;

	@Column(length=1)
	private String situacao;

//	//bi-directional many-to-one association to Atividade
//	@OneToMany(mappedBy="tipoAtividade")
//	private List<Atividade> atividades;

	//bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name="iddepartamento")
	private Departamento departamento;

	public TipoAtividade() {
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
//		atividade.setTipoAtividade(this);
//
//		return atividade;
//	}
//
//	public Atividade removeAtividade(Atividade atividade) {
//		getAtividades().remove(atividade);
//		atividade.setTipoAtividade(null);
//
//		return atividade;
//	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

}