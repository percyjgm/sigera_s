package com.sigera.dao;

import com.sigera.entidad.Persona;
import java.util.List;

public interface PersonaDao extends GenericDao<Persona,Integer>{

    public List<Persona> obtenerPersonasSinCuenta();
    
    public boolean existeDni(String dni);
    
    public String obtenerDniPorId(Integer id);
}
