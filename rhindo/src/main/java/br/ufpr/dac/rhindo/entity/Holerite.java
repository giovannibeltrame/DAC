package br.ufpr.dac.rhindo.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the holerite database table.
 * 
 */
@Entity
@Table(name="holerite")
@NamedQuery(name="Holerite.findAll", query="SELECT h FROM Holerite h")
public class Holerite implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HoleritePK id;

	@Column(name="quantidadehorastrabalhadas")
	private Integer quantidadeHorasTrabalhadas;

	@Column(name="salariobruto", precision=8, scale=2)
	private BigDecimal salarioBruto;

	@Column(name="salarioliquido", precision=8, scale=2)
	private BigDecimal salarioLiquido;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="idfuncionario", nullable=false, insertable=false, updatable=false)
	private Funcionario funcionario;

	public Holerite() {
	}

	public HoleritePK getId() {
		return this.id;
	}

	public void setId(HoleritePK id) {
		this.id = id;
	}

	public Integer getQuantidadeHorasTrabalhadas() {
		return this.quantidadeHorasTrabalhadas;
	}

	public void setQuantidadeHorasTrabalhadas(Integer quantidadeHorasTrabalhadas) {
		this.quantidadeHorasTrabalhadas = quantidadeHorasTrabalhadas;
	}

	public BigDecimal getSalarioBruto() {
		return this.salarioBruto;
	}

	public void setSalarioBruto(BigDecimal salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public BigDecimal getSalarioLiquido() {
		return this.salarioLiquido;
	}

	public void setSalarioLiquido(BigDecimal salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}