package com.sigera.dao.impl;

import com.sigera.dao.UsuarioDao;
import com.sigera.entidad.Usuario;
import com.sigera.hibernate.HibernateImpl;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("usuarioImpl")
public class UsuarioImpl extends HibernateImpl<Usuario, Integer> implements UsuarioDao, Serializable {

    private static final long serialVersionUID = 3594392339260918654L;

    @Override
    public boolean existeUsuario(Usuario usuario) {
        boolean resultado = false;
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(Usuario.class, "user")
                .add(Restrictions.eq("user.nick", usuario.getNick()))
                .add(Restrictions.eq("user.clave", usuario.getClave()));
        Usuario user = (Usuario) criteria.uniqueResult();
        if (user != null && user.getClave().equals(usuario.getClave())) {
            resultado = true;
        }
        return resultado;
    }

    @Override
    public Usuario getUsuarioPorNick(String nick) {
        Usuario auxUsuario = null;
        Session session = getSessionFactory().getCurrentSession();
        try {
            Criteria criteria = session.createCriteria(Usuario.class, "user").add(Restrictions.eq("user.nick", nick));
            auxUsuario = (Usuario) criteria.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auxUsuario;
    }

    @Override
    public Usuario getUsuarioSessionPorNick(String nick) {
        Usuario auxUsuario = null;
        Query query = null;
        List<Usuario> lista;
        try {
            query = getSessionFactory().getCurrentSession()
                    .createQuery("select distinct user from Usuario as user "
                            + "join fetch user.persona as ope join fetch user.perfil as p join fetch p.permisos "
                            + "where user.nick = :parameter");
            query.setString("parameter", nick);
            lista = query.list();
            if (lista.size() > 1) {
                throw new Exception("El método esta devolviendo más de un usuario");
            }
            auxUsuario = lista.get(0);
        } catch (HibernateException e) {

            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auxUsuario;
    }

    @Override
    public Usuario obtenerUsuarioPorIdConDependencias(Integer id) {
        Usuario auxUsuario = null;
        Session session = getSessionFactory().getCurrentSession();
        try {
            Criteria criteria = session.createCriteria(Usuario.class , "usuario")
                    .createCriteria("usuario.perfil")
                    .createCriteria("usuario.persona")
                    .add(Restrictions.eq("usuario.id", id));
            auxUsuario = (Usuario) criteria.uniqueResult();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return auxUsuario;
    }

    @Override
    public String obtenerNickUsuarioPorId(Integer id) {
        String nick = "";
        Session session = getSessionFactory().getCurrentSession();
        try {
            Query query = session.createQuery("select usuario.nick from Usuario as usuario where usuario.id = :dato");
            query.setInteger("dato", id);
            nick = (String) query.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return nick;
    }

}
