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

    PersonasMae entidad;

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

    public PersonasMae editarRegistro(PersonasMae obj) {

        entidad = new PersonasMae();
        entidad = buscarRegistro(obj.getPersonaId().toString());

        entidad.setNombres(obj.getNombres());
        entidad.setPrimerApellido(obj.getPrimerApellido());
        entidad.setSegundoApellido(obj.getSegundoApellido());
        entidad.setEstadoCivil(obj.getEstadoCivil());
        entidad.setSexo(obj.getSexo());
        entidad.setFecAlta(obj.getFecAlta());
        entidad.setFecBaja(obj.getFecBaja());
        entidad.setFecNacimiento(obj.getFecNacimiento());
        entidad.setTipoDocumento(obj.getTipoDocumento());
        entidad.setPersonasDireccion(obj.getPersonasDireccion());

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

    public PersonasMae buscarRegistro(String id) {
        PersonasMae obj = new PersonasMae();
        obj = em.find(PersonasMae.class, Integer.valueOf(id));
         return obj;
    }

    public PersonasMae buscarNroDocumento(String nroDoc) {

        entidad = new PersonasMae();

        Query Qry = em.createNamedQuery("PersonasMae.BuscarNroDocumento", PersonasMae.class)
                .setParameter("nrodocumento", nroDoc);

        entidad = (PersonasMae) Qry.getSingleResult();

        if (entidad == null) {
            return entidad; //throw new msjDataVacia("Pais no encontrado");
        } else {
            return entidad;
        }

    }

}
