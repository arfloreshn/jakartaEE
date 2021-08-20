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
import model.CatPais;
import model.PersonasDireccion;
import model.PersonasMae;

/**
 *
 * @author AllanRamiro
 */
@Stateless
public class CatalagoPersonasControl {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbLeccionesPU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    public List<PersonasMae> ListarTodo() {
        Query Qry = em.createNamedQuery("PersonasMae.ListarTodo", PersonasMae.class);
        return Qry.getResultList();
    }

    public PersonasMae crearRegistro(PersonasMae mae, PersonasDireccion direccion) {
        try {
            tx.begin();

            mae.setPersonasDireccion(direccion);
            direccion.setPersonasMae(mae);
            em.persist(direccion);
            em.flush();

            tx.commit();
        } catch (javax.persistence.PersistenceException ex) {
            if (em.getTransaction().isActive()) {
                System.out.println(ex.getMessage() + ex.getCause().getMessage().toString());
                em.getTransaction().rollback();
            }
        } catch (Exception ex2) {
            System.out.println(ex2.getMessage());
            em.getTransaction().rollback();

        }
        return mae;
    }

    public PersonasMae buscarRegistro(String id) {
        PersonasMae obj = new PersonasMae();
        obj = em.find(PersonasMae.class, Integer.valueOf(id));
        if (obj == null) {
            return obj; //throw new msjDataVacia("Pais no encontrado");
        } else {
            return obj;
        }

    }

    public PersonasMae buscarNroDocumento(String nroDoc) {

         PersonasMae obj = new PersonasMae();
      
        Query Qry = em.createNamedQuery("PersonasMae.BuscarNroDocumento", PersonasMae.class)
                .setParameter("nrodocumento", nroDoc);

         obj = (PersonasMae) Qry.getSingleResult();
     
        if (obj == null) {
            return obj; //throw new msjDataVacia("Pais no encontrado");
        } else {
            return obj;
        }

    }

}
