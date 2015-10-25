package com.sigera.hibernate;

import com.sigera.paginacion.Paginador;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class HibernatePaginador<T> extends Paginador<T> {

    protected static final Logger log = Logger.getLogger(HibernatePaginador.class.getPackage().getName());
    
    private static final long serialVersionUID = -211521190854179763L;

    private Session session = null;

    @Autowired
    SessionFactory sessionFactory;

    public HibernatePaginador() {
    }
    
    /**
    * Método que retorna un objeto por el cual se puede filtrar una búsqueda. La
    * clase que extienda podra definir el tipo de objeto que este retorna.
    * @return Object
    **/
    protected abstract Object createFilter();
    
    /**
    * Método que retorna un objeto por el cual se puede filtrar una búsqueda. La
    * clase que extienda podra definir el tipo de objeto que este retorna.
     * @param values
    * @return Object
    **/
    protected abstract Object createFilter(Object ... values);

    protected void openSession() {
        if (session == null || !session.isOpen()) {
            session = getSessionFactory().openSession();
        }
    }

    protected void closeSession() {
        if (session != null) {
            session.close();
        }
    }

    /**
     * GETTERS AND SETTERS *
     */
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        return session;
    }
    
}
