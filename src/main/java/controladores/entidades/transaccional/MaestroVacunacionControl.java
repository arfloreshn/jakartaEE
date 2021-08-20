/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.entidades.transaccional;

import excepciones.msjDataVacia;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import model.CatCeaten;
import model.CatVacunas;
import model.MaeVacunacion;

/**
 *
 * @author AllanRamiro
 */
@Stateless
public class MaestroVacunacionControl {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbLeccionesPU");
    EntityManager em = emf.createEntityManager();

    MaeVacunacion maestroVacunacion;
    List<Tuple> list;

    public List<MaeVacunacion> listarMaestroVacunacion() {
        Query Qry = em.createNamedQuery("MaeVacunacion.ListarTodo", MaeVacunacion.class);
        return Qry.getResultList();
    }

    public List<MaeVacunacion> buscarPorDescripcion(String var_descripcion) {
        Query Qry = em.createNamedQuery("MaeVacunacion.BuscarPorNombrePaciente", MaeVacunacion.class)
                .setParameter("nombrePaciente", var_descripcion.toUpperCase() + "%");

        return Qry.getResultList();
    }

    public List<MaeVacunacion> listarRangoFechas(Date fecInicio, Date fecFinal) {
        Query Qry = em.createNamedQuery("MaeVacunacion.buscarPorRangoFechas", MaeVacunacion.class)
                .setParameter("inicio", fecInicio, TemporalType.DATE)
                .setParameter("final", fecFinal, TemporalType.DATE);
        return Qry.getResultList();
    }

    public List<CatVacunas> listarVacunas() {
        Query Qry = em.createNamedQuery("CatVacunas.ListarTodo", CatVacunas.class);
        return Qry.getResultList();
    }

    public List<CatCeaten> listarCeaten() {
        Query Qry = em.createNamedQuery("CatCeaten.ListarTodo", CatCeaten.class);
        return Qry.getResultList();
    }

    public MaeVacunacion buscarRegistro(String id) {
        maestroVacunacion = new MaeVacunacion();
        maestroVacunacion = em.find(MaeVacunacion.class, Integer.valueOf(id));

        if (maestroVacunacion == null) {
            throw new msjDataVacia("registro no encontrado");
        } else {
            return maestroVacunacion;
        }

    }

    public MaeVacunacion crearRegistro(MaeVacunacion obj) {

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

    public MaeVacunacion editarRegistro(MaeVacunacion obj) {
        maestroVacunacion = buscarRegistro(obj.getVacunacionId().toString());

        maestroVacunacion.setCatCeaten(obj.getCatCeaten());
        maestroVacunacion.setCatVacunas(obj.getCatVacunas());

        try {
            em.getTransaction().begin();
            em.merge(maestroVacunacion);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }

        return maestroVacunacion;
    }

    public List<Tuple> graficodePie() {

        try {
            em.getTransaction().begin();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
            Root<MaeVacunacion> mae = cq.from(MaeVacunacion.class);
            cq.multiselect(mae.get("edad"), cb.count(mae)).groupBy(mae.get("edad"));

            list = em.createQuery(cq).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }

        return list;

    }

    public List<Tuple> graficoporHospitales() {

        try {

            em.getTransaction().begin();
            Metamodel m = em.getMetamodel();
            EntityType<CatCeaten> det_ = m.entity(CatCeaten.class);
            EntityType<MaeVacunacion> mae_ = m.entity(MaeVacunacion.class);

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Tuple> criterio = cb.createQuery(Tuple.class);

            Root<CatCeaten> mae = criterio.from(CatCeaten.class);
            Join<CatCeaten, MaeVacunacion> det = mae.join("maeVacunacionList", JoinType.INNER);

            criterio.groupBy(mae.get("descripcionCeaten"));
            criterio.multiselect(mae.get("descripcionCeaten"), cb.count(det));

            list = em.createQuery(criterio).getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }

        return list;

    }

}
