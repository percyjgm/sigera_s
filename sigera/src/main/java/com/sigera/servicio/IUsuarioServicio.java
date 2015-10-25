/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigera.servicio;

import com.sigera.entidad.Persona;
import com.sigera.entidad.Perfil;
import com.sigera.entidad.Usuario;
import java.util.List;

public interface IUsuarioServicio {
    public boolean agregarUsuario(Usuario usuario);   
    
    public boolean actualizarUsuario(Usuario usuario, Persona persona, Perfil perfil);   
    
    public List<Persona> obtenerPersona();
    
    public List<Persona> obtenerPersonasSinCuenta();
    
    public List<Perfil> obtenerPerfil();
    
    public Usuario obtenerUsuarioPorId(Integer id);
   
    public Usuario obtenerUsuarioCompletoPorId(Integer id);
    
    public void eliminarUsuario(Usuario usuario);
}
