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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    public Professor(Integer i, Integer j, String string) {
        this.codigo = i;
        this.siape = j;
        this.nome = string;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((siape == null) ? 0 : siape.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Professor other = (Professor) obj;
        if (codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        } else if (!codigo.equals(other.codigo)) {
            return false;
        }
        if (siape == null) {
            if (other.siape != null) {
                return false;
            }
        } else if (!siape.equals(other.siape)) {
            return false;
        }
        return true;
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

    @Override
    public String toString() {
        return getSiape() + " - " + getNome();
    }
}