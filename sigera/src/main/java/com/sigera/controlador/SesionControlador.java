package com.sigera.controlador;

import com.sigera.entidad.Modulo;
import com.sigera.entidad.ModuloPadre;
import com.sigera.entidad.Permiso;
import com.sigera.entidad.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@ManagedBean(name = "sesionControlador")
@Scope("session")
public class SesionControlador implements Serializable {

    private static final Logger log = Logger.getLogger(SesionControlador.class.getPackage().getName());
    private static final long serialVersionUID = -7799872652395433181L;

    Usuario usuarioSesion;

    List<ModuloPadre> menuSistema;

    Modulo moduloSelecionado;

    NavegadorControlador navegadorControlador;

    public SesionControlador() {
        FacesContext context = FacesContext.getCurrentInstance();
        navegadorControlador = (NavegadorControlador) context.getExternalContext().getApplicationMap().get("navegadorControlador");
    }

    public void ensamblarMenu() {
        ensamblarMenu(usuarioSesion.getPerfil().getPermisos());
    }

    public void ensamblarMenu(Collection<Permiso> permisos) {
        menuSistema = new ArrayList<>();
        for (Permiso permiso : permisos) {
            if (permiso.getListar()) {
                permiso.getModulo().setPermiso(permiso);
                agregarSubMenu(menuSistema, permiso.getModulo());
            }
        }
        Collections.sort(menuSistema);

    }

    public String redireccionar(String url, int indexModPadre, int indexMod) {
        log.info(String.format("[SesionControlador/redireccionar] Se ha obtenido los valores de redirección: url = %s | indexPadre = %d | indexHijo = %d", url, indexModPadre, indexMod));
        ModuloPadre padre = menuSistema.get(indexModPadre);
        moduloSelecionado = (Modulo) padre.getModulos().toArray()[indexMod];
        return url;
    }

    public void validarModuloPostBack() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (!context.isPostback()) {
            HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
            String contextPath = origRequest.getRequestURI();
            log.info("[SesionControlador/validarModuloPostBack] El Path es : " + contextPath);
            if (moduloSelecionado != null) {
                Modulo moduloPrevio = SerializationUtils.clone(moduloSelecionado);
                log.info("[SesionControlador/validarModuloPostBack] Modulo previamente seleccionado : " + moduloSelecionado.getNombre());
                contextPath = contextPath.replaceAll("/JStand", "");
                String viewId = navegadorControlador.obtenerViewId(contextPath);
                if(!viewId.isEmpty()){
                    moduloSelecionado = obtenerModuloSesion(viewId);
                }
                if(moduloSelecionado == null){
                    moduloSelecionado = moduloPrevio;
                }
                log.info("[SesionControlador/validarModuloPostBack] Modulo seleccionado : " + moduloSelecionado.getNombre());
            }
        }
    }

    private void agregarSubMenu(List<ModuloPadre> menuSistema, Modulo modulo) {
        boolean selected = false;
        if (menuSistema.isEmpty()) {
            menuSistema.add(modulo.getModuloPadre());
            menuSistema.get(0).setModulos(new TreeSet<Modulo>());
            menuSistema.get(0).getModulos().add(modulo);
        } else {
            for (ModuloPadre menu : menuSistema) {
                if (menu.getModulos().isEmpty()) {
                    menu.setModulos(new TreeSet<Modulo>());
                }
                if (menu.getId().intValue() == modulo.getModuloPadre().getId().intValue()) {
                    menu.getModulos().add(modulo);
                    selected = true;
                    break;
                }
            }
            if (!selected) {
                menuSistema.add(modulo.getModuloPadre());
                menuSistema.get(menuSistema.size() - 1).setModulos(new TreeSet<Modulo>());
                menuSistema.get(menuSistema.size() - 1).getModulos().add(modulo);
            }
        }

    }

    private Modulo obtenerModuloSesion(String casoNavegacionActual) {
        for (ModuloPadre padre : menuSistema) {
            for (Modulo hijo : padre.getModulos()) {
                if (casoNavegacionActual.equals(hijo.getUrl())) {
                    return hijo;
                }
            }
        }
        return null;
    }

    /**
     * GETTERS AND SETTERS *
     */
    public Usuario getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(Usuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public List<ModuloPadre> getMenuSistema() {
        return menuSistema;
    }

    public void setMenuSistema(List<ModuloPadre> menuSistema) {
        this.menuSistema = menuSistema;
    }

    public Modulo getModuloSelecionado() {
        return moduloSelecionado;
    }

    public void setModuloSelecionado(Modulo moduloSelecionado) {
        this.moduloSelecionado = moduloSelecionado;
    }

}
