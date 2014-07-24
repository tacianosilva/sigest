package br.ufrn.cerescaico.bsi.sigest.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the convenio database table.
 */
@Entity
@Table(name="convenio")
public class Convenio implements Serializable, Bean {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="CONVENIO_CODIGO_GENERATOR", sequenceName="CONVENIO_CODIGO_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONVENIO_CODIGO_GENERATOR")
    @Column(unique=true, nullable=false)
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
    private Empresa empresaBean;

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

    public Empresa getEmpresaBean() {
        return this.empresaBean;
    }

    public void setEmpresaBean(Empresa empresaBean) {
        this.empresaBean = empresaBean;
    }

}