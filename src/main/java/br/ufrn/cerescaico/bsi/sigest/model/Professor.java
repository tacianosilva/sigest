package br.ufrn.cerescaico.bsi.sigest.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the professor database table.
 *
 */
@Entity
@Table(name="professor")
public class Professor implements Serializable, Bean {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="PROFESSOR_CODIGO_GENERATOR", sequenceName="PROFESSOR_CODIGO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROFESSOR_CODIGO_GENERATOR")
    @Column(unique=true, nullable=false)
    private Integer codigo;

    @Column(nullable=false, length=255)
    private String nome;

    @Column(nullable=false)
    private Integer siape;

    //bi-directional many-to-one association to Avaliacao
    @OneToMany(mappedBy="professor")
    private List<Avaliacao> avaliacaos;

    //bi-directional many-to-one association to Estagio
    @OneToMany(mappedBy="professor")
    private List<Estagio> estagios;

    //bi-directional many-to-one association to Curso
    @ManyToOne
    @JoinColumn(name="curso", nullable=false)
    private Curso curso;

    public Professor() {
    }

    public Professor(int i, int j, String string) {
        // TODO Auto-generated constructor stub
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSiape() {
        return this.siape;
    }

    public void setSiape(Integer siape) {
        this.siape = siape;
    }

    public List<Avaliacao> getAvaliacaos() {
        return this.avaliacaos;
    }

    public void setAvaliacaos(List<Avaliacao> avaliacaos) {
        this.avaliacaos = avaliacaos;
    }

    public List<Estagio> getEstagios() {
        return this.estagios;
    }

    public void setEstagios(List<Estagio> estagios) {
        this.estagios = estagios;
    }

    public Curso getCurso() {
        return this.curso;
    }

    public void setCurso(Curso cursoBean) {
        this.curso = cursoBean;
    }

}