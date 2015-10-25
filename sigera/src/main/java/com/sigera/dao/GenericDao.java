package com.sigera.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T,ID extends Serializable> {
    
    public void agregar(T t);
    
    public void actualizar(T t);
    
    public void eliminar(T t);
    
    public T obtenerEntidad(ID id);
    
    public List<T> obtenerTodos();
    
    /**
     * Este metodo nos permite traer todos los registros que tienen estado 1 (Activo).
     * Lanza una excepcion si la entidad a traer no tiene una columna estado.
     */
    public List<T> obtenerTodosActivos();
    
    public void recargarEntidad(T t);
    
}
