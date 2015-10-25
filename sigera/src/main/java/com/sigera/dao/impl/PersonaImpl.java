package com.sigera.dao.impl;

import com.sigera.dao.PersonaDao;
import com.sigera.entidad.Persona;
import com.sigera.exceptions.DataBaseException;
import com.sigera.hibernate.HibernateImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("operadorImpl")
public class PersonaImpl extends HibernateImpl<Persona,Integer> implements PersonaDao,Serializable
{

    private static final Logger log = Logger.getLogger(PersonaImpl.class.getPackage().getName());
    private static final long serialVersionUID = 3807602853676226494L;
    
    @Override
    public List<Persona> obtenerPersonasSinCuenta() {
        List<Persona> operadores = new ArrayList<>();
        Session session = null;
        try{
            session = getSessionFactory().getCurrentSession();
            Query query = session.createQuery("from Operador as operador where operador.id not in (select user.operador.id from Usuario as user)");
            operadores = query.list();
        }catch(HibernateException e){
            log.error("[PersonaImpl/obtenerPersonasSinCuenta]", e);
            throw new DataBaseException("Se encontro un error al traer a los operadores");
        }
        return operadores;
    }

    @Override
    public boolean existeDni(String dni) {
        boolean resultado = false;
        try{
            Session session = getSessionFactory().getCurrentSession();
            Query query = session.createQuery("select ope.dni from Operador as ope where ope.dni = :dato");
            query.setString("dato", dni);
            if(query.uniqueResult() != null){
                resultado = true;
            }
        }catch(HibernateException e){
            throw new DataBaseException("Existio un error al comprobar el dni");
        }
        return resultado;
    }

    @Override
    public String obtenerDniPorId(Integer id) {
        String dniOriginal= "";
        try{
            Session session = getSessionFactory().getCurrentSession();
            Query query = session.createQuery("select ope.dni from Operador as ope where ope.id = :dato");
            query.setInteger("dato", id);
            dniOriginal = (String) query.uniqueResult();
        }catch(HibernateException e){
            throw new DataBaseException("Error al buscar el dni original");
        }
        return dniOriginal;
    }
    
    

}
