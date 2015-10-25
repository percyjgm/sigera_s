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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Grado generated by hbm2java
 */
@Entity
@Table(name = "GRADO"
)
public class Grado implements java.io.Serializable {

    private Integer idGrado;
    private String nombre;
    private Set<ListaMateriales> listaMateriales = new HashSet(0);
    private Set<Seccion> secciones = new HashSet(0);
    private Set<Matricula> matriculas = new HashSet(0);

    public Grado() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "ID_GRADO", unique = true, nullable = false)
    public Integer getIdGrado() {
        return this.idGrado;
    }

    public void setIdGrado(Integer idGrado) {
        this.idGrado = idGrado;
    }

    @Column(name = "VC_NOMBRE", nullable = false, length = 45)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grado")
    public Set<ListaMateriales> getListaMateriales() {
        return this.listaMateriales;
    }

    public void setListaMateriales(Set<ListaMateriales> listaMateriales) {
        this.listaMateriales = listaMateriales;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grado")
    public Set<Seccion> getSecciones() {
        return this.secciones;
    }

    public void setSecciones(Set<Seccion> secciones) {
        this.secciones = secciones;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grado")
    public Set<Matricula> getMatriculas() {
        return this.matriculas;
    }

    public void setMatriculas(Set<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

}