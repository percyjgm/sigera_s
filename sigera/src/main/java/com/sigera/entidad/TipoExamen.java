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
 * TipoExamen generated by hbm2java
 */
@Entity
@Table(name = "TIPO_EXAMEN"
)
public class TipoExamen implements java.io.Serializable {

    private Integer idTipoExamen;
    private String nombre;
    private Set<Examen> examens = new HashSet(0);

    public TipoExamen() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "ID_TIPO_EXAMEN", unique = true, nullable = false)
    public Integer getIdTipoExamen() {
        return this.idTipoExamen;
    }

    public void setIdTipoExamen(Integer idTipoExamen) {
        this.idTipoExamen = idTipoExamen;
    }

    @Column(name = "VC_NOMBRE", nullable = false, length = 45)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoExamen")
    public Set<Examen> getExamens() {
        return this.examens;
    }

    public void setExamens(Set<Examen> examens) {
        this.examens = examens;
    }

}
