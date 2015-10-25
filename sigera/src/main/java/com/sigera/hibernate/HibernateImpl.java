package com.sigera.hibernate;

import com.sigera.dao.GenericDao;
import com.sigera.entidad.State;
import com.sigera.exceptions.DataBaseException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

    public static final Logger log = Logger.getLogger(HibernateImpl.class.getPackage().getName());

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void agregar(T t) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.save(t);
        } catch (HibernateException e) {
            log.error(String.format("[HibernateImpl/agregar] %s", e.getMessage()), e);
            throw new DataBaseException("Ha existido un error al guardar la información");
        } catch (Exception e) {
            log.error(String.format("[HibernateImpl/agregar] %s", e.getMessage()), e);
            throw new DataBaseException("Ha existido un error al guardar la información");
        }
    }

    @Override
    public void actualizar(T t) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.update(t);
        } catch (HibernateException e) {
            log.error(String.format("[HibernateImpl/actualizar] %s", e.getMessage()), e);
            throw new DataBaseException("Ha existido un error al actualizar la información");
        } catch (Exception e) {
            log.error(String.format("[HibernateImpl/actualizar] %s", e.getMessage()), e);
            throw new DataBaseException("Ha existido un error al actualizar la información");
        }
    }

    @Override
    public void eliminar(T t) {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.delete(t);
        } catch (HibernateException e) {
            log.error(String.format("[HibernateImpl/eliminar] %s", e.getMessage()), e);
            throw new DataBaseException("Ha existido un error al guardar la información");
        } catch (Exception e) {
            log.error(String.format("[HibernateImpl/eliminar] %s", e.getMessage()), e);
            throw new DataBaseException("Ha existido un error al guardar la información");
        }
    }

    @Override
    public T obtenerEntidad(ID id) {
        T t = null;
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            t = (T) session.get(getEntityClass(), id);
        } catch (HibernateException e) {
            log.error(String.format("[HibernateImpl/obtenerEntidad] %s", e.getMessage()), e);
            throw new DataBaseException("Ha existido un error al guardar la información");
        } catch (Exception e) {
            log.error(String.format("[HibernateImpl/obtenerEntidad] %s", e.getMessage()), e);
            throw new DataBaseException("Ha existido un error al guardar la información");
        }
        return t;
    }

    @Override
    public List<T> obtenerTodos() {
        List<T> lista = new ArrayList<T>();
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            lista = session.createCriteria(getEntityClass()).list();
        } catch (HibernateException e) {
            log.error(String.format("[HibernateImpl/obtenerTodos] %s", e.getMessage()), e);
            throw new DataBaseException("Ha existido un error al guardar la información");
        } catch (Exception e) {
            log.error(String.format("[HibernateImpl/obtenerTodos] %s", e.getMessage()), e);
            throw new DataBaseException("Ha existido un error al guardar la información");
        }
        return lista;
    }

    @Override
    public List<T> obtenerTodosActivos() {
        List<T> lista = new ArrayList<T>();
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(getEntityClass());
            criteria.add(Restrictions.eq("estado", State.ACTIVO));
            lista = criteria.list();
        } catch (HibernateException e) {
            log.error(String.format("[HibernateImpl/obtenerTodos] %s", e.getMessage()), e);
            throw new DataBaseException("Ha existido un error al obtener la información");
        } catch (Exception e) {
            log.error(String.format("[HibernateImpl/obtenerTodos] %s", e.getMessage()), e);
            throw new DataBaseException("Ha existido un error al obtener la información");
        }
        return lista;
    }

    @Override
    public void recargarEntidad(T t) {
        Session session = null;
        session = sessionFactory.getCurrentSession();
        session.refresh(t);
    }

    private Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * GETTERS AND SETTERS *
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
