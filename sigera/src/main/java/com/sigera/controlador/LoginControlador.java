package com.sigera.controlador;

import com.sigera.entidad.Usuario;
import com.sigera.servicio.IPersonaServicio;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "loginControlador")
@Scope("request")
public class LoginControlador implements Serializable{
    
    private static final Logger log = Logger.getLogger(LoginControlador.class .getPackage().getName());
    private static final long serialVersionUID = 156531402288459601L;
    
    @Autowired
    IPersonaServicio personaServicio;
    private Usuario usuario;
    /** Sesion **/
    SesionControlador sesion;

    public LoginControlador(){
        usuario = new Usuario();
    }
    
    public String login()
    {
        Usuario usuarioSesion = null; 
        if((usuarioSesion = personaServicio.generarUsuarioSesion(usuario)) == null)
        {
            return "";
        }
        log.info("[LoginControlador] Creando al usuarioSesion");
        sesion = new SesionControlador(); 
        sesion.setUsuarioSesion(usuarioSesion);
        sesion.ensamblarMenu();
        log.info("[LoginControlador] Se termino de ensamblar al usuarioSesion");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sesionControlador",sesion);
        return "views/home.xhtml?faces-redirect=true";
    }
    
    public String logout()
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        if (session != null) {
            session.invalidate();
        }
        return "salir";
    }

    /**
     * GETTERS AND SETTERS *
     */
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    
}
