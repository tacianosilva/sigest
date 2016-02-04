package br.ufrn.cerescaico.bsi.sigest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the convenio database table.
 */
@Entity
@Table(name="convenio")
public class Convenio implements Bean {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer codigo;

    @Temporal( TemporalType.DATE)
    @Column(name="data_fim", nullable=false)
    private Date dataFim;

    @Temporal( TemporalType.DATE)
    @Column(name="data_inicio", nullable=false)
    private Date dataInicio;

    @Column(nullable=false, length=255)
    private String descricao;

    @Column(nullable=false)
    private byte[] documento;

    //bi-directional many-to-one association to Empresa
    @ManyToOne
    @JoinColumn(name="empresa", nullable=false)
    private Empresa empresa;

    public Convenio() {
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getDocumento() {
        return this.documento;
    }

    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}