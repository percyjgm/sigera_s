package com.sigera.entidad;
// Generated 24/10/2015 08:59:36 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ActividadesExtracurricularesId generated by hbm2java
 */
@Embeddable
public class ActividadesExtracurricularesId implements java.io.Serializable {

    private int idActividadesExtracurriculares;
    private int idProfesor;

    public ActividadesExtracurricularesId() {
    }

    public ActividadesExtracurricularesId(int idActividadesExtracurriculares, int idProfesor) {
        this.idActividadesExtracurriculares = idActividadesExtracurriculares;
        this.idProfesor = idProfesor;
    }

    @Column(name = "ID_ACTIVIDADES_EXTRACURRICULARES", nullable = false)
    public int getIdActividadesExtracurriculares() {
        return this.idActividadesExtracurriculares;
    }

    public void setIdActividadesExtracurriculares(int idActividadesExtracurriculares) {
        this.idActividadesExtracurriculares = idActividadesExtracurriculares;
    }

    @Column(name = "ID_PROFESOR", nullable = false)
    public int getIdProfesor() {
        return this.idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if ((other == null)) {
            return false;
        }
        if (!(other instanceof ActividadesExtracurricularesId)) {
            return false;
        }
        ActividadesExtracurricularesId castOther = (ActividadesExtracurricularesId) other;

        return (this.getIdActividadesExtracurriculares() == castOther.getIdActividadesExtracurriculares())
                && (this.getIdProfesor() == castOther.getIdProfesor());
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + this.getIdActividadesExtracurriculares();
        result = 37 * result + this.getIdProfesor();
        return result;
    }

}
