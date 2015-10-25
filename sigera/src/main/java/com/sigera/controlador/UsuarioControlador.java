package com.sigera.controlador;

import com.sigera.entidad.Persona;
import com.sigera.entidad.Perfil;
import com.sigera.entidad.Usuario;
import com.sigera.paginacion.Paginador;
import com.sigera.servicio.IUsuarioServicio;
import com.sigera.utils.Utilitario;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "usuarioControlador")
@Scope("view")
public class UsuarioControlador implements Serializable {

    private static final long serialVersionUID = 7129761685134584331L;

    @Autowired
    private IUsuarioServicio usuarioServicio;

    Usuario usuario;

    List<Persona> personas;
    Persona persona;

    List<Perfil> perfiles;
    Perfil perfil;

    @Autowired
    @Qualifier("usuarioPaginador")
    Paginador<Usuario> usuarioPaginador;

    public UsuarioControlador() {
    }

    public void initList() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            usuarioPaginador.initPaginador();
        }
    }

    public void initCreate() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            usuario = new Usuario();
            persona = new Persona();
            personas = usuarioServicio.obtenerPersonasSinCuenta();
            perfil = new Perfil();
            perfiles = usuarioServicio.obtenerPerfil();
        }
    }

    public void initUpdate() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            Object value = Utilitario.getFlash("idUsuario");
            if (value == null) {
                Utilitario.redireccionarJSF(FacesContext.getCurrentInstance(), "/views/cuenta/list.xhtml");
                return;
            }
            usuario = usuarioServicio.obtenerUsuarioCompletoPorId(Integer.parseInt(value.toString()));
            persona = new Persona();
            persona.setNombre(usuario.getPersona().getNombre());
            persona.setApellidoPaterno(usuario.getPersona().getApellidoPaterno());
            persona.setApellidoMaterno(usuario.getPersona().getApellidoMaterno());
            persona.setIdPersona(usuario.getPersona().getIdPersona());
            personas = usuarioServicio.obtenerPersonasSinCuenta();
            personas.add(usuario.getPersona());
            perfil = new Perfil();
            perfil.setId(usuario.getPerfil().getId());
            perfil.setNombre(usuario.getPerfil().getNombre());
            perfiles = usuarioServicio.obtenerPerfil();
        }
    }

    public void guardarUsuario() {
        usuario.setPersona(persona);
        usuario.setPerfil(perfil);
        if (usuarioServicio.agregarUsuario(usuario)) {
            limpiar();
        }
    }

    public void actualizarUsuario(){
        usuarioServicio.actualizarUsuario(usuario, persona, perfil);
    }
    
    public String irActualizar(Integer id) {
        Utilitario.putFlash("idUsuario", id);
        return "update.xhtml?faces-redirect=true";
    }

    public void eliminarUsuario() {
        usuarioServicio.eliminarUsuario(usuario);
    }

    public void capturarUsuario(Integer id) {
        usuario = usuarioServicio.obtenerUsuarioPorId(id);
    }

    private void limpiar() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Paginador<Usuario> getUsuarioPaginador() {
        return usuarioPaginador;
    }

    public void setUsuarioPaginador(Paginador<Usuario> usuarioPaginador) {
        this.usuarioPaginador = usuarioPaginador;
    }

}
