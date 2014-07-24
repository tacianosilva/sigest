package br.ufrn.cerescaico.bsi.sigest.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the avaliacao database table.
 */
@Entity
@Table(name="avaliacao")
public class Avaliacao implements Serializable, Bean {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="AVALIACAO_CODIGO_GENERATOR", sequenceName="AVALIACAO_CODIGO_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AVALIACAO_CODIGO_GENERATOR")
    @Column(unique=true, nullable=false)
    private Integer codigo;

    @Column(nullable=false, length=255)
    private String comentario;

    @Column(nullable=false)
    private float nota;

    //bi-directional many-to-one association to Estagio
    @ManyToOne
    @JoinColumn(name="estagio", nullable=false)
    private Estagio estagioBean;

    //bi-directional many-to-one association to Professor
    @ManyToOne
    @JoinColumn(name="avaliador", nullable=false)
    private Professor professor;

    public Avaliacao() {
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public float getNota() {
        return this.nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Estagio getEstagioBean() {
        return this.estagioBean;
    }

    public void setEstagioBean(Estagio estagioBean) {
        this.estagioBean = estagioBean;
    }

    public Professor getProfessor() {
        return this.professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

}