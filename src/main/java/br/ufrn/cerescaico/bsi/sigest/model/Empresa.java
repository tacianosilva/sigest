package br.ufrn.cerescaico.bsi.sigest.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the empresa database table.
 * 
 */
@Entity
@Table(name="empresa")
public class Empresa implements Serializable, Bean {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EMPRESA_CODIGO_GENERATOR", sequenceName="EMPRESA_CODIGO_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMPRESA_CODIGO_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigo;

	@Column(nullable=false, length=15)
	private String cnpj;

	@Column(nullable=false, length=255)
	private String nome;

	//bi-directional many-to-one association to Convenio
	@OneToMany(mappedBy="empresaBean")
	private List<Convenio> convenios;

	//bi-directional many-to-one association to Estagio
	@OneToMany(mappedBy="empresaBean")
	private List<Estagio> estagios;

	//bi-directional many-to-one association to Supervisor
	@OneToMany(mappedBy="empresa")
	private List<Supervisor> supervisors;

    public Empresa() {
    }

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Convenio> getConvenios() {
		return this.convenios;
	}

	public void setConvenios(List<Convenio> convenios) {
		this.convenios = convenios;
	}
	
	public List<Estagio> getEstagios() {
		return this.estagios;
	}

	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
	}
	
	public List<Supervisor> getSupervisors() {
		return this.supervisors;
	}

	public void setSupervisors(List<Supervisor> supervisors) {
		this.supervisors = supervisors;
	}
	
}