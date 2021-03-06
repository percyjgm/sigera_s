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
 * Tarea generated by hbm2java
 */
@Entity
@Table(name = "TAREA"
)
public class Tarea implements java.io.Serializable {

    private Integer idTarea;
    private CursoPorProfesor cursoPorProfesor;
    private boolean estado;
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaModificacion;

    public Tarea() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "ID_TAREA", unique = true, nullable = false)
    public Integer getIdTarea() {
        return this.idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CURSO_POR_PROFESOR", nullable = false)
    public CursoPorProfesor getCursoPorProfesor() {
        return this.cursoPorProfesor;
    }

    public void setCursoPorProfesor(CursoPorProfesor cursoPorProfesor) {
        this.cursoPorProfesor = cursoPorProfesor;
    }

    
    @Column(name = "TY_ESTADO", nullable = false)
    public boolean getEstado() {
        return this.estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Column(name = "VC_NOMBRE", nullable = false, length = 45)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "VC_DESCRIPCION", nullable = false, length = 45)
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FECHA_CREACION")
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FECHA_MODIFICACION")
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

}
