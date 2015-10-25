package com.sigera.paginacion;

import java.util.List;
import org.primefaces.model.LazyDataModel;

public abstract class Paginador<T> extends LazyDataModel<T>{
    
    private static final long serialVersionUID = 7481733480895998320L;
    
    /**
     * Método que retorna la lista completa de objetos <T> de la paginación 
     * @return List<T>
     **/
    public abstract List<T> obtenerListaCompleta();
    
    /**
     * Método que inicializa los posibles valores a utilizar la implementación,
     * este método debera ejecutarse antes de realizar todas las operaciones.
     **/
    public abstract void initPaginador();
    
    /**
     * Método que inicializa los posibles valores a utilizar la implementación,
     * este método debera ejecutarse antes de realizar todas las operaciones.
     * @param values
     **/
    public abstract void initPaginador(Object ... values);
    
    /**
     * Método que inicializa los posibles valores a utilizar la implementación,
     * este método debera ejecutarse antes de realizar todas las operaciones.
     * @return un objeto Class de la clase evaluada por la anotación Paginator
     **/
    protected Class getClassImplement(){
        return getClass().getAnnotation(Paginator.class).value();
    }

}
