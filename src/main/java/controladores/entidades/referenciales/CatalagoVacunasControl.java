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
import model.CatVacunas;

/**
 *
 * @author AllanRamiro
 */
@Stateless
public class CatalagoVacunasControl {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbLeccionesPU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    
    CatVacunas  entidad;
    
    public List<CatVacunas> ListarTodo() {
        Query Qry = em.createNamedQuery("CatVacunas.ListarTodo", CatVacunas.class);
        return Qry.getResultList();
    }
     
        public CatVacunas editarRegistro(CatVacunas obj) {
        entidad = buscarRegistro(obj.getVacunaID().toString());

        entidad.setDescripcionVacuna(obj.getDescripcionVacuna());
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


    
    public CatVacunas buscarRegistro(String id) {
        entidad = new CatVacunas();
        entidad = em.find(CatVacunas.class, Integer.valueOf(id));

        if (entidad == null) {
            return entidad; //throw new msjDataVacia("Pais no encontrado");
        } else {
            return entidad;
        }

    }
    
  
}
