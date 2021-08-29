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
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.CatProductos;

/**
 *
 * @author AllanRamiro
 */
@Stateless
public class CatalagoProductosControl {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbLeccionesPU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    private CatProductos entidad;
    
    public List<CatProductos> ListarTodo() {
        Query Qry = em.createNamedQuery("CatProductos.ListarTodo", CatProductos.class);
        return Qry.getResultList();
    }

    public CatProductos buscarRegistro(String id) {
        entidad = new CatProductos();
       entidad = em.find(CatProductos.class, Integer.valueOf(id));
        if (entidad == null) {
            return entidad; //throw new msjDataVacia("Pais no encontrado");
        } else {
            return entidad;
        }
    }
         
    public CatProductos editarRegistro(CatProductos pro){

        entidad = new CatProductos();
        entidad = buscarRegistro(pro.getProductoId().toString());
        
        entidad.setCodBarra(pro.getCodBarra());
        entidad.setCostoVta(pro.getCostoVta());
        entidad.setNomFoto(pro.getNomFoto());

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
    
    
    public CatProductos buscarCodBarra(String codBarra) {

         entidad = new CatProductos();
      
        Query Qry = em.createNamedQuery("CatProductos.BuscarCodigoBarra", CatProductos.class)
                .setParameter("codbarra", codBarra);

         entidad = (CatProductos) Qry.getSingleResult();
     
        if (entidad == null) {
            return entidad; //throw new msjDataVacia("Pais no encontrado");
        } else {
            return entidad;
        }

    }
    
}
