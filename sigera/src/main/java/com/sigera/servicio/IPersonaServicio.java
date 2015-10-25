package com.sigera.servicio;

import com.sigera.entidad.Persona;
import com.sigera.entidad.Usuario;

public interface IPersonaServicio {
    
    public void agregarPersona(Persona persona);
    
    public void actualizarPersona(Persona persona);
    
    public void eliminarPersona(Persona persona);
    
    public Persona obtenerPersona(Integer id);
    
    public boolean existeCuenta(Usuario usuario);
    
    public Usuario generarUsuarioSesion(Usuario usuario); 
    
}
