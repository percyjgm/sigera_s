package com.sigera.entidad;
// Generated 07/10/2015 04:43:51 PM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "USUARIO"
)
public class Usuario implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_USUARIO", unique = true, nullable = false)
    private Integer idUsuario;
    
    @Column(name = "TY_ESTADO", nullable = false)
    private boolean ty_estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERFIL", nullable = false)
    private Perfil perfil;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERSONA", nullable = false)
    private Persona persona;
    @Column(name = "VC_NICK", nullable = false, length = 45)
    private String nick;
    @Column(name = "VC_CLAVE", nullable = false, length = 45)
    private String clave;
    @Column(name = "VC_CREADO_POR", length = 45)
    private String creadoPor;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "VC_MODIFICADO_POR")
    private String modificadoPor;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FECHA_MODIFICACION")
    private Date fechaModificado;

    public Usuario() {
    }

    public Integer getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean getEstado() {
        return this.ty_estado;
    }

    public void setEstado(boolean estado) {
        this.ty_estado = estado;
    }

    public Perfil getPerfil() {
        return this.perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCreadoPor() {
        return this.creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getModificadoPor() {
        return this.modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Date getFechaModificado() {
        return this.fechaModificado;
    }

    public void setFechaModificado(Date fechaModificado) {
        this.fechaModificado = fechaModificado;
    }

}
