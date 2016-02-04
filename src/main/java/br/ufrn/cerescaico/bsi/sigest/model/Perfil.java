package br.ufrn.cerescaico.bsi.sigest.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the perfil database table.
 */
@Entity
@Table(name="perfil")
public class Perfil implements Serializable, Bean {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable=false, length=255)
    private String descricao;

    @Column(nullable=false, length=255)
    private String nome;

    //bi-directional many-to-one association to Perfi
    @OneToMany(mappedBy="perfil")
    private List<Perfis> perfis;

    public Perfil() {
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Perfis> getPerfis() {
        return this.perfis;
    }

    public void setPerfis(List<Perfis> perfis) {
        this.perfis = perfis;
    }

}