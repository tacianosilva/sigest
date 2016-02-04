package br.ufrn.cerescaico.bsi.sigest.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the perfis database table.
 *
 */
@Entity
@Table(name="perfis")
public class Perfis implements Serializable, Bean {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer codigo;

    //bi-directional many-to-one association to Perfil
    @ManyToOne
    @JoinColumn(name="codigo_perfil", nullable=false)
    private Perfil perfil;

    //bi-directional many-to-one association to Usuario
    @ManyToOne
    @JoinColumn(name="codigo_usuario", nullable=false)
    private Usuario usuario;

    public Perfis() {
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Perfil getPerfil() {
        return this.perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}