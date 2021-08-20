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

    private CatProductos producto;
    
    public List<CatProductos> ListarTodo() {
        Query Qry = em.createNamedQuery("CatProductos.ListarTodo", CatProductos.class);
        return Qry.getResultList();
    }

    public CatProductos buscarRegistro(String id) {
        CatProductos obj = new CatProductos();
        obj = em.find(CatProductos.class, Integer.valueOf(id));
        if (obj == null) {
            return obj; //throw new msjDataVacia("Pais no encontrado");
        } else {
            return obj;
        }
    }
         
    public CatProductos editarRegistro(CatProductos pro){

        producto = buscarRegistro(pro.getProductoId().toString());
        producto.setNomFoto(pro.getNomFoto());

        try {
            em.getTransaction().begin();
            em.merge(producto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }

        return producto;
    }

    public CatProductos buscarCodBarra(String codBarra) {

         CatProductos obj = new CatProductos();
      
        Query Qry = em.createNamedQuery("CatProductos.BuscarCodigoBarra", CatProductos.class)
                .setParameter("codbarra", codBarra);

         obj = (CatProductos) Qry.getSingleResult();
     
        if (obj == null) {
            return obj; //throw new msjDataVacia("Pais no encontrado");
        } else {
            return obj;
        }

    }
    
}
