package com.sigera.seguridad;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SesionFilter implements Filter{
    
    private FilterConfig config;
    private String urlLogin;

    @Override
    public void init(FilterConfig fc) throws ServletException {
         this.config = fc;
        //Tambien se pueden cargar los parametros
        //Configura url desconexión
        this.urlLogin = config.getInitParameter("urlLogin");
        if (urlLogin == null || urlLogin.trim().length() == 0) {
            throw new ServletException("No se ha configurado URL de login");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Object sesion = ((HttpServletRequest) request).getSession().getAttribute("sesionControlador");
        if(sesion == null){
            ((HttpServletResponse) response).sendRedirect("/Sigera/index.xhtml");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        config = null;
    }

}
