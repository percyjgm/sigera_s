package com.sigera.servicio.impl;

import com.sigera.dao.PersonaDao;
import com.sigera.dao.PerfilDao;
import com.sigera.dao.UsuarioDao;
import com.sigera.entidad.Persona;
import com.sigera.entidad.Perfil;
import com.sigera.entidad.Usuario;
import com.sigera.servicio.IUsuarioServicio;
import com.sigera.utils.Utilitario;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UsuarioServicio implements IUsuarioServicio, Serializable {

    private static final long serialVersionUID = -4111128316806300754L;

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    PersonaDao operadorDao;

    @Autowired
    PerfilDao perfilDao;

    @Transactional(readOnly = false)
    @Override
    public boolean agregarUsuario(Usuario usuario) {
        usuario.setNick(usuario.getNick().toLowerCase());
        if (existeNick(usuario.getNick())) {
            Utilitario.enviarMensajeGlobalError("El usuario ya existe");
            return false;
        }
        usuarioDao.agregar(usuario);
        Utilitario.enviarMensajeGlobalValido("Se ha ingresado correctamente");
        return true;
    }

    @Transactional(readOnly = false)
    @Override
    public boolean actualizarUsuario(Usuario usuario, Persona persona, Perfil perfil) {
        usuario.setNick(usuario.getNick().toLowerCase());
        String original = usuarioDao.obtenerNickUsuarioPorId(usuario.getIdUsuario());
        if (!original.equalsIgnoreCase(usuario.getNick()) && existeNick(usuario.getNick())) {
            Utilitario.enviarMensajeGlobalError("El usuario ya existe");
            return false;
        }
        usuario.setPersona(persona);
        usuario.setPerfil(perfil);
        usuarioDao.actualizar(usuario);
        Utilitario.enviarMensajeGlobalValido("Se ha actualizado correctamente");
        return true;
    }

    @Override
    public List<Persona> obtenerPersona() {
        return operadorDao.obtenerTodos();
    }

    @Override
    public List<Perfil> obtenerPerfil() {
        return perfilDao.obtenerTodos();
    }

    @Override
    public List<Persona> obtenerPersonasSinCuenta() {
        return operadorDao.obtenerPersonasSinCuenta();
    }

    private boolean existeNick(String nick) {
        Usuario usuario = usuarioDao.getUsuarioPorNick(nick);
        return usuario != null;
    }

    @Override
    public Usuario obtenerUsuarioPorId(Integer id) {
        return usuarioDao.obtenerEntidad(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminarUsuario(Usuario usuario) {
        usuarioDao.eliminar(usuario);
        Utilitario.enviarMensajeGlobalValido("Se ha eliminado correctamente");
    }

    @Override
    public Usuario obtenerUsuarioCompletoPorId(Integer id) {
        return usuarioDao.obtenerUsuarioPorIdConDependencias(id);
    }
}
