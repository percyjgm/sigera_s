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
 * Profesor generated by hbm2java
 */
@Entity
@Table(name = "PROFESOR"
)
public class Profesor implements java.io.Serializable {

    private Integer idProfesor;
    
    private Persona persona;
    private Set<Tutor> tutors = new HashSet(0);
    private Set<ActividadesExtracurriculares> actividadesExtracurriculareses = new HashSet(0);
    
    private Set<CursoPorProfesor> cursoPorProfesors = new HashSet(0);

    public Profesor() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "ID_PROFESOR", unique = true, nullable = false)
    public Integer getIdProfesor() {
        return this.idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

   

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERSONA", nullable = false)
    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profesor")
    public Set<Tutor> getTutors() {
        return this.tutors;
    }

    public void setTutors(Set<Tutor> tutors) {
        this.tutors = tutors;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profesor")
    public Set<ActividadesExtracurriculares> getActividadesExtracurriculareses() {
        return this.actividadesExtracurriculareses;
    }

    public void setActividadesExtracurriculareses(Set<ActividadesExtracurriculares> actividadesExtracurriculareses) {
        this.actividadesExtracurriculareses = actividadesExtracurriculareses;
    }

   


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profesor")
    public Set<CursoPorProfesor> getCursoPorProfesors() {
        return this.cursoPorProfesors;
    }

    public void setCursoPorProfesors(Set<CursoPorProfesor> cursoPorProfesors) {
        this.cursoPorProfesors = cursoPorProfesors;
    }

}
