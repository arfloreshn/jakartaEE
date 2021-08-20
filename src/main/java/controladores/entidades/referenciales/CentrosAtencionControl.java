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
import model.CatCeaten;

/**
 *
 * @author AllanRamiro
 */
@Stateless
public class CentrosAtencionControl {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbLeccionesPU");
    EntityManager em = emf.createEntityManager();

    CatCeaten objHospital;
    List<CatCeaten> lstHospital;

    public CatCeaten crearRegistro(CatCeaten obj) {

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

    public CatCeaten editarRegistro(CatCeaten obj) {
        objHospital = buscarRegistro(obj.getCeatenID().toString());

        objHospital.setCeatenID(obj.getCeatenID());
        objHospital.setDescripcionCeaten(obj.getDescripcionCeaten());
        try {
            em.getTransaction().begin();
            em.merge(objHospital);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }

        return objHospital;
    }

    public boolean quitarRegistro(String id) {
        objHospital = buscarRegistro(id);
        try {
            em.getTransaction().begin();
            em.remove(objHospital);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return true;
    }

    public List<CatCeaten> findAll() {
        Query Qry = em.createNamedQuery("CatCeaten.listarTodo", CatCeaten.class);
        return Qry.getResultList();
    }

    public CatCeaten buscarRegistro(String id) {
        objHospital = new CatCeaten();
        objHospital = em.find(CatCeaten.class, Integer.valueOf(id));

        if (objHospital == null) {
            return objHospital; //throw new msjDataVacia("Pais no encontrado");
        } else {
            return objHospital;
        }

    }

    public List<CatCeaten> buscarPorDescripcion(String var_descripcion) {
        Query Qry = em.createNamedQuery("CatCeaten.Buscar_x_descripcion", CatCeaten.class)
                .setParameter("descripPais", var_descripcion);

        return Qry.getResultList();
    }

}
