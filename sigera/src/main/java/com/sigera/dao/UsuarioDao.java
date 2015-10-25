package com.sigera.dao;

import com.sigera.entidad.Usuario;

public interface UsuarioDao extends GenericDao<Usuario, Integer> {

    public boolean existeUsuario(Usuario usuario);

    public Usuario getUsuarioPorNick(String nick);

    public String obtenerNickUsuarioPorId(Integer id);

    public Usuario getUsuarioSessionPorNick(String nick);

    public Usuario obtenerUsuarioPorIdConDependencias(Integer id);

}
