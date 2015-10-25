package com.sigera.utils;

import java.util.Map;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.FacesRequestAttributes;

public class ViewScope implements Scope {

    public static final String VIEW_SCOPE_CALLBACKS = "viewScope.callbacks";

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> viewMap = getViewMap();
        Object instance = viewMap.get(name);
        if (instance == null) {
            instance = objectFactory.getObject();
            synchronized (viewMap) {
                viewMap.put(name, instance);
            }
        } else {
            //logger.debug("Going to return an existing bean '{}'", instance);
        }
        return instance;
    }

    @Override
    public Object remove(String name) {
        Object instance = getViewMap().remove(name);
        if (instance != null) {
            Map<String, Runnable> callbacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
            if (callbacks != null) {
                callbacks.remove(name);
                //    logger.debug("Bean '{}' has been removed.", instance);
            }
        }
        return instance;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable runnable) {
        Map<String, Runnable> callbacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
        if (callbacks != null) {
            callbacks.put(name, runnable);
        //    logger.debug("Registered callback for '{}'", name);
        }
    }

    @Override
    public Object resolveContextualObject(String name) {
         FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
        return facesRequestAttributes.resolveReference(name);
    }

    @Override
    public String getConversationId() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
        return facesRequestAttributes.getSessionId() + "-" + facesContext.getViewRoot().getViewId();
    }

    private Map<String, Object> getViewMap() {
        return FacesContext.getCurrentInstance().getViewRoot().getViewMap();
    }

}
