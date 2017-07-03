package br.ufpr.dac.rhindo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the cargo database table.
 * 
 */
@Entity
@Table(name="cargo")
@NamedQuery(name="Cargo.findAll", query="SELECT c FROM Cargo c")
public class Cargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_cargo", sequenceName="seq_cargo", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_cargo")
	@Column(name="idcargo", unique=true, nullable=false)
	private Long id;

	@Column(length=1)
	private String gerente;

	@Column(length=255)
	private String nome;

	@Column(name="percentualimposto", precision=8, scale=2)
	private BigDecimal percentualImposto;

	@Column(name="quantidademinimahorasmes")
	private Integer quantidadeMinimaHorasMes;

	@Column(precision=8, scale=2)
	private BigDecimal salario;

	@Column(length=1)
	private String situacao;

//	//bi-directional many-to-one association to CargoAtribuidoFuncionario
//	@OneToMany(mappedBy="cargo")
//	private List<CargoAtribuidoFuncionario> cargoAtribuidoFuncionarios;
//
//	//bi-directional many-to-many association to Requisito
//	@ManyToMany(mappedBy="cargos")
//	private List<Requisito> requisitos;

	public Cargo() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGerente() {
		return this.gerente;
	}

	public void setGerente(String gerente) {
		this.gerente = gerente;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPercentualImposto() {
		return this.percentualImposto;
	}

	public void setPercentualImposto(BigDecimal percentualImposto) {
		this.percentualImposto = percentualImposto;
	}

	public Integer getQuantidadeMinimaHorasMes() {
		return this.quantidadeMinimaHorasMes;
	}

	public void setQuantidadeMinimaHorasMes(Integer quantidadeMinimaHorasMes) {
		this.quantidadeMinimaHorasMes = quantidadeMinimaHorasMes;
	}

	public BigDecimal getSalario() {
		return this.salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
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
//		cargoAtribuidoFuncionario.setCargo(this);
//
//		return cargoAtribuidoFuncionario;
//	}
//
//	public CargoAtribuidoFuncionario removeCargoatribuidofuncionario(CargoAtribuidoFuncionario cargoAtribuidoFuncionario) {
//		getCargoAtribuidoFuncionarios().remove(cargoAtribuidoFuncionario);
//		cargoAtribuidoFuncionario.setCargo(null);
//
//		return cargoAtribuidoFuncionario;
//	}
//
//	public List<Requisito> getRequisitos() {
//		return this.requisitos;
//	}
//
//	public void setRequisitos(List<Requisito> requisitos) {
//		this.requisitos = requisitos;
//	}

}