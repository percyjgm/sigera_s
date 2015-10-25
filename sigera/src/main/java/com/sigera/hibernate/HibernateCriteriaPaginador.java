package com.sigera.hibernate;

import com.sigera.exceptions.DataBaseException;
import static com.sigera.hibernate.HibernatePaginador.log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Luis Alonso Ballena Garcia Clase que pagina a nivel de objetos
 * DetachedCriteria
 * @deprecated Esta clase no tiene una implementación correcta para las funcionalidad 
 * requeridas por Hibernate por lo cual queda en desuso.
 */
public class HibernateCriteriaPaginador<T> extends HibernatePaginador<T> {

    private static final long serialVersionUID = 4345427164427961630L;

    private DetachedCriteria filtroCriteria = null;

    private DetachedCriteria totalCriteria = null;

    private DetachedCriteria listCriteria = null;

    private DetachedCriteria allListCriteria = null;
    
    private Set<String> alias = new HashSet<String>(0);

    public HibernateCriteriaPaginador() {

    }

    @Override
    public void initPaginador() {
        try {
            filtroCriteria = createFilter();
            cloneCriteria();
        } catch (HibernateException e) {
            log.error("[HibernatePaginador/initFiltro] Error en creación del DetachedCriteria", e);
            throw new DataBaseException("Error al activar la funcionalidad lazy del Datatable");
        } catch (Exception e) {
            log.error("[HibernatePaginador/initFiltro] Error al iniciar variables del Paginador", e);
            throw new DataBaseException(e.getMessage());
        }
    }
    
    @Override
    protected DetachedCriteria createFilter() {
        return DetachedCriteria.forClass(getClassImplement(), getClassImplement().getSimpleName().toLowerCase());
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        openSession();
        int totalRows = getTotalRows();
        log.debug("[HibernatePaginador/load] total de filas : " + totalRows);
        setRowCount(totalRows);
        Criteria dataCriteria = getListElementsForPage(first, pageSize);
        dataCriteria = agregarOrdenamiento(dataCriteria, sortField, sortOrder);
        List<T> auxList = executeCriteriaList(dataCriteria);
        closeSession();
        return auxList;
    }

    public Criteria agregarOrdenamiento(Criteria criteria, String sortField, SortOrder sortOrder) {
        String nivelPrincipal = getClassImplement().getSimpleName().toLowerCase();
        if (sortField != null) {
            log.debug("[HibernatePaginador/agregarOrdenamiento] valor actual del SortField : " + sortField);
            if (sortField.contains(".")) {
                String[] niveles = sortField.split("\\.");
                log.debug("[HibernatePaginador/agregarOrdenamiento] niveles del SortField : " + niveles.length);
                criteria = definirAlias(criteria, niveles, nivelPrincipal);
                sortField = niveles[niveles.length - 2] + "." + niveles[niveles.length - 1];
            } else {
                sortField = nivelPrincipal + "." + sortField;
            }
            log.debug("[HibernatePaginador/agregarOrdenamiento] nuevo valor del SortField : " + sortField);
            criteria.addOrder(sortOrder == SortOrder.ASCENDING ? Order.asc(sortField) : Order.desc(sortField));
        }
        return criteria;
    }

    private List<T> executeCriteriaList(Criteria criteria) {
        return criteria.list();
    }

    @Override
    public List<T> obtenerListaCompleta() {
        openSession();
        List<T> lista = null;
        try {
            lista = allListCriteria.getExecutableCriteria(getSession()).list();
        } catch (HibernateException e) {
            log.error("[HibernatePaginador/obtenerListaCompleta] Error al obtener todos los elementos de la paginación", e);
            throw new DataBaseException("No se pudo obtener la lista completa de la paginación");
        } finally {
            closeSession();
        }
        return lista;
    }

    private int getTotalRows() {
        int totalRows = 0;
        totalCriteria.setProjection(Projections.rowCount());
        totalRows = ((Long) (totalCriteria.getExecutableCriteria(getSession()).uniqueResult())).intValue();
        return totalRows;
    }

    private Criteria getListElementsForPage(int first, int pageSize) {
        return listCriteria.getExecutableCriteria(getSession()).setFirstResult(first).setMaxResults(pageSize);
    }

    private void cloneCriteria() {
        try {
            this.totalCriteria = copyDetachedCriteria(filtroCriteria);
            this.listCriteria = copyDetachedCriteria(filtroCriteria);
            this.allListCriteria = copyDetachedCriteria(filtroCriteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Criteria definirAlias(Criteria criteria, String niveles[], String nivelPrincipal) {
        String nivelActual = nivelPrincipal;
        for (int i = 0; i < niveles.length - 1; i++) {
            criteria.createAlias(nivelActual + "." + niveles[i], niveles[i]);
        }
        return criteria;
    }

    private DetachedCriteria copyDetachedCriteria(DetachedCriteria detachedCriteria) throws IOException, ClassNotFoundException {
        try {
            ByteArrayOutputStream baostream = new ByteArrayOutputStream();
            ObjectOutputStream oostream = new ObjectOutputStream(baostream);
            oostream.writeObject(detachedCriteria);
            oostream.flush();
            oostream.close();
            ByteArrayInputStream baistream = new ByteArrayInputStream(baostream.toByteArray());
            ObjectInputStream oistream = new ObjectInputStream(baistream);
            DetachedCriteria copy = (DetachedCriteria) oistream.readObject();
            oistream.close();
            return copy;
        } catch (IOException e) {
            throw e;
        } catch (ClassNotFoundException e) {
            throw e;
        }
    }

    @Override
    protected Object createFilter(Object... values) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initPaginador(Object... values) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
