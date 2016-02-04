package br.ufrn.cerescaico.bsi.sigest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the avaliacao database table.
 */
@Entity
@Table(name="avaliacao")
public class Avaliacao implements Bean {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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