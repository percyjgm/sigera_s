package com.sigera.entidad;
// Generated 07/10/2015 04:43:51 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Seccion generated by hbm2java
 */
@Entity
@Table(name = "SECCION"
)
public class Seccion implements java.io.Serializable {

    private Integer idSeccion;
    private Grado grado;
    private String nombre;
    private Set<Tutor> tutores = new HashSet(0);
    private Set<Curso> cursos = new HashSet(0);

    public Seccion() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "ID_SECCION", unique = true, nullable = false)
    public Integer getIdSeccion() {
        return this.idSeccion;
    }

    public void setIdSeccion(Integer idSeccion) {
        this.idSeccion = idSeccion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GRADO", nullable = false)
    public Grado getGrado() {
        return this.grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    @Column(name = "VC_NOMBRE", nullable = false, length = 45)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seccion")
    public Set<Tutor> getTutores() {
        return this.tutores;
    }

    public void setTutores(Set<Tutor> tutores) {
        this.tutores = tutores;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seccion")
    public Set<Curso> getCursos() {
        return this.cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }

}
