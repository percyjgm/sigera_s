package com.sigera.entidad;
// Generated 07/10/2015 04:43:51 PM by Hibernate Tools 4.3.1

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Persona generated by hbm2java
 */
@Entity
@Table(name = "PERSONA"
)
public class Persona implements java.io.Serializable {

    private Integer idPersona;
    
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private String dni;
    private boolean sexo;
    private String telefono;
    private String celular;
    private String email;
    private Date fechaNacimiento;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private Set<Apoderado> apoderados = new HashSet(0);
    private Set<Profesor> profesores = new HashSet(0);
    private Set<Alumno> alumnos = new HashSet(0);
    private Set<Usuario> usuarios = new HashSet(0);

    public Persona() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "ID_PERSONA", unique = true, nullable = false)
    public Integer getIdPersona() {
        return this.idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

   
    @Column(name = "VC_DIRECCION", nullable = false)
    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name = "VC_NOMBRE", nullable = false, length = 45)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "VC_APELLIDO_PATERNO", nullable = false, length = 45)
    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    @Column(name = "VC_APELLIDO_MATERNO", nullable = false, length = 45)
    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    @Column(name = "IN_DNI", nullable = false, length = 45)
    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Column(name = "TY_SEXO", nullable = false)
    public boolean isSexo() {
        return this.sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    @Column(name = "VC_TELEFONO", nullable = false, length = 15)
    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Column(name = "VC_CELULAR", length = 45)
    public String getCelular() {
        return this.celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Column(name = "VC_EMAIL", nullable = false, length = 45)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FECHA_NACIMIENTO", nullable = false, length = 19)
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FECHA_REGISTRO", length = 19)
    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_FECHA_MODIFICACION", length = 19)
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "persona")
    public Set<Apoderado> getApoderados() {
        return this.apoderados;
    }

    public void setApoderados(Set<Apoderado> apoderados) {
        this.apoderados = apoderados;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "persona")
    public Set<Profesor> getProfesores() {
        return this.profesores;
    }

    public void setProfesores(Set<Profesor> profesores) {
        this.profesores = profesores;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "persona")
    public Set<Alumno> getAlumnos() {
        return this.alumnos;
    }

    public void setAlumnos(Set<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "persona")
    public Set<Usuario> getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
