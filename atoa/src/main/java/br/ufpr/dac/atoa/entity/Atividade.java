package br.ufpr.dac.atoa.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the atividade database table.
 * 
 */
@Entity
@Table(name="atividade")
@NamedQuery(name="Atividade.findAll", query="SELECT a FROM Atividade a")
public class Atividade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_atividade", sequenceName="seq_atividade", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_atividade")
	@Column(name="idatividade", unique=true, nullable=false)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datahoracadastro")
	private Date dataHoraCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datahorainicio")
	private Date dataHoraInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datahorafim")
	private Date dataHoraFim;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datahoraprevisaoinicio")
	private Date dataHoraPrevisaoInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datahoraprevisaofim")
	private Date dataHoraPrevisaoFim;

	@Column(length=255)
	private String descricao;

	//bi-directional many-to-one association to Funcionario
	@ManyToOne
	@JoinColumn(name="idfuncionario")
	private Funcionario funcionario;

	//bi-directional many-to-one association to TipoAtividade
	@ManyToOne
	@JoinColumn(name="idtipoatividade")
	private TipoAtividade tipoAtividade;

	public Atividade() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataHoraCadastro() {
		return this.dataHoraCadastro;
	}

	public void setDataHoraCadastro(Date dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	public Date getDataHoraFim() {
		return this.dataHoraFim;
	}

	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	public Date getDataHoraInicio() {
		return this.dataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Date getDataHoraPrevisaoFim() {
		return this.dataHoraPrevisaoFim;
	}

	public void setDataHoraPrevisaoFim(Date dataHoraPrevisaoFim) {
		this.dataHoraPrevisaoFim = dataHoraPrevisaoFim;
	}

	public Date getDataHoraPrevisaoInicio() {
		return this.dataHoraPrevisaoInicio;
	}

	public void setDataHoraPrevisaoInicio(Date dataHoraPrevisaoInicio) {
		this.dataHoraPrevisaoInicio = dataHoraPrevisaoInicio;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public TipoAtividade getTipoAtividade() {
		return this.tipoAtividade;
	}

	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

}