package br.ufrn.cerescaico.bsi.sigest.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the supervisor database table.
 *
 */
@Entity
@Table(name="supervisor")
public class Supervisor implements Serializable, Bean {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable=false, length=255)
    private String nome;

    //bi-directional many-to-one association to Empresa
    @ManyToOne
    @JoinColumn(name="cod_empresa", nullable=false)
    private Empresa empresa;

    public Supervisor() {
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

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}