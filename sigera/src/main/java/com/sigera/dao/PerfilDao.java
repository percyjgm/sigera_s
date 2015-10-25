package com.sigera.dao;

import com.sigera.entidad.Perfil;
import java.util.List;


public interface PerfilDao extends GenericDao<Perfil, Integer> {

    public List<Perfil> obtenerPerfilConPermisosPorId(Integer id);
}
