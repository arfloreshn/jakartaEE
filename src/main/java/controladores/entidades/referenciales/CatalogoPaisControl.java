/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.entidades.referenciales;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import model.CatPais;

/**
 *
 * @author AllanRamiro
 */
@Stateless
@XmlRootElement()
@XmlAccessorType (XmlAccessType.FIELD)
public class CatalogoPaisControl  {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbLeccionesPU");
    EntityManager em = emf.createEntityManager();

    CatPais entidad;
    List<CatPais> lstPaises;

  
    public CatPais crearRegistro(CatPais obj) {

        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return obj;

    }

    public CatPais editarRegistro(CatPais obj) {
        entidad = buscarRegistro(obj.getPaisId().toString());

        entidad.setDescripPais(obj.getDescripPais());
    
        try {
            em.getTransaction().begin();
            em.merge(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }

        return entidad;
    }

    public boolean quitarRegistro(String id) {
        entidad = buscarRegistro(id);
        try {
            em.getTransaction().begin();
            em.remove(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return true;
    }

     @XmlElement
    public List<CatPais> ListarTodo() {
        Query Qry = em.createNamedQuery("CatPais.ListarTodo", CatPais.class);
        return Qry.getResultList();
    }

    public CatPais buscarRegistro(String id) {
        entidad = new CatPais();
         entidad  = em.find(CatPais.class, Integer.valueOf(id));

        if (entidad == null) {
            return entidad; //throw new msjDataVacia("Pais no encontrado");
        } else {
            return entidad;
        }

    }

    
    
    public List<CatPais> buscarPorDescripcion(String var_descripcion) {
            Query Qry = em.createNamedQuery("CatPais.Buscar_x_descripcion", CatPais.class)
                .setParameter("descripPais", var_descripcion.toUpperCase() +"%");

        return Qry.getResultList();
    }


}
