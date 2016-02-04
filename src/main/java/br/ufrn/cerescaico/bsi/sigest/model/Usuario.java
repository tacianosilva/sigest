package br.ufrn.cerescaico.bsi.sigest.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 *
 */
@Entity
@Table(name="usuario")
public class Usuario implements Serializable, Bean {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable=false, length=255)
    private String email;

    @Column(nullable=false, length=255)
    private String nome;

    @Column(nullable=false, length=255)
    private String senha;

    //bi-directional many-to-one association to Perfi
    @OneToMany(mappedBy="usuario")
    private List<Perfis> perfis;

    public Usuario() {
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Perfis> getPerfis() {
        return this.perfis;
    }

    public void setPerfis(List<Perfis> perfis) {
        this.perfis = perfis;
    }

}