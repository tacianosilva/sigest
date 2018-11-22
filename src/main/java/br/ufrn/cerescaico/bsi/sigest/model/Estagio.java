package br.ufrn.cerescaico.bsi.sigest.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the estagio database table.
 */
@Entity
@Table(name="estagio")
public class Estagio implements Serializable, Bean {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer codigo;

    @Temporal( TemporalType.DATE)
    @Column(name="data_cadastro", nullable=false)
    private Date dataCadastro;

    @Temporal( TemporalType.DATE)
    @Column(name="data_fim", nullable=false)
    private Date dataFim;

    @Temporal( TemporalType.DATE)
    @Column(name="data_inicio", nullable=false)
    private Date dataInicio;

    @Column(nullable=false, length=255)
    private String descricao;

    @Column(nullable=false)
    private float media;

    /**
     * Coluna para upload do relatório em pdf.
     */
    @Column
    private byte[] relatorio;

    @Column(nullable=false, length=50)
    private String situacao;

    //bi-directional many-to-one association to Avaliacao
    @OneToMany(mappedBy="estagioBean")
    private List<Avaliacao> avaliacoes;

    //bi-directional many-to-one association to Empresa
    @ManyToOne
    @JoinColumn(name="empresa", nullable=false)
    private Empresa empresa;

    //bi-directional many-to-one association to Estagiario
    @ManyToOne
    @JoinColumn(name="estagiario", nullable=false)
    private Estagiario estagiario;

    //bi-directional many-to-one association to Professor
    @ManyToOne
    @JoinColumn(name="orientador", nullable=false)
    private Professor professor;

    public Estagio() {
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getDataCadastro() {
        return this.dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
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

    public float getMedia() {
        return this.media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public byte[] getRelatorio() {
        return this.relatorio;
    }

    public void setRelatorio(byte[] relatorio) {
        this.relatorio = relatorio;
    }

    public String getSituacao() {
        return this.situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public List<Avaliacao> getAvaliacoes() {
        return this.avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Estagiario getEstagiario() {
        return this.estagiario;
    }

    public void setEstagiario(Estagiario estagiario) {
        this.estagiario = estagiario;
    }

    public Professor getProfessor() {
        return this.professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

}