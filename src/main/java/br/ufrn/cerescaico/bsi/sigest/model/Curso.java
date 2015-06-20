package br.ufrn.cerescaico.bsi.sigest.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the curso database table.
 *
 */
@Entity
@Table(name="curso")
public class Curso implements Serializable, Bean {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="CURSO_CODIGO_GENERATOR", sequenceName="CURSO_CODIGO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CURSO_CODIGO_GENERATOR")
    @Column(unique=true, nullable=false)
    private Integer codigo;

    @Column(nullable=false, length=255)
    private String nome;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Professor coordenador;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Professor viceCoordenador;

    //bi-directional many-to-one association to Estagiario
    @OneToMany(mappedBy="cursoBean")
    private List<Estagiario> estagiarios;

    //bi-directional many-to-one association to Professor
    @OneToMany(mappedBy="curso")
    private List<Professor> professors;

    public Curso() {
    }

    public Curso(int i, String string) {
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

    public Professor getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Professor coordenador) {
        this.coordenador = coordenador;
    }

    public Professor getViceCoordenador() {
        return viceCoordenador;
    }

    public void setViceCoordenador(Professor vice) {
        this.viceCoordenador = vice;
    }

    public List<Estagiario> getEstagiarios() {
        return this.estagiarios;
    }

    public void setEstagiarios(List<Estagiario> estagiarios) {
        this.estagiarios = estagiarios;
    }

    public List<Professor> getProfessors() {
        return this.professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    @Override
    public String toString() {
        return getCodigo() + " - " + getNome();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) obj;
        if (codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        } else if (!codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }
}