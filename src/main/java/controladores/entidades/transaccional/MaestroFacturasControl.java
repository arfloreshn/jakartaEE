/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.entidades.transaccional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.FacturaMae;

/**
 *
 * @author AllanRamiro
 */
@Stateless
public class MaestroFacturasControl {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbLeccionesPU");
    EntityManager em = emf.createEntityManager();

    public FacturaMae crearRegistro(FacturaMae obj) {

        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.flush();

            em.getTransaction().commit();
        } catch (javax.persistence.PersistenceException ex) {
            if (em.getTransaction().isActive()) {
                System.out.println(ex.getMessage() + ex.getCause().getMessage());
                em.getTransaction().rollback();
            }
        } catch (Exception ex2) {
            System.out.println(ex2.getMessage());
            em.getTransaction().rollback();

        }
        return obj;

    }

}