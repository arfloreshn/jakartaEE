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
    
    
    public List<CatVacunas> ListarTodo() {
        Query Qry = em.createNamedQuery("CatVacunas.ListarTodo", CatVacunas.class);
        return Qry.getResultList();
    }
            
  
}
