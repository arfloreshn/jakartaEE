/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.entidades.operativas;

import controladores.entidades.transaccional.MaestroVacunacionControl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.CatCeaten;
import model.CatVacunas;
import model.MaeVacunacion;

/**
 *
 * @author AllanRamiro
 */
@Named
@ViewScoped
public class maestroVacunacionesView implements Serializable {

    @Inject
    MaestroVacunacionControl vacunacionController;

    int codVacuna;
    int codCeaten;
    MaeVacunacion maeVacunacion;

    CatVacunas catVacunas;
    CatCeaten catCeaten;

    List<CatVacunas> vacunas;
    List<CatCeaten> ceaten;
    List<MaeVacunacion> lista;

    @PostConstruct
    public void init() {
        //codVacuna = 3;
        vacunas = vacunacionController.listarVacunas();
        ceaten = vacunacionController.listarCeaten();

        maeVacunacion = new MaeVacunacion();
         lista = new ArrayList();
        lista = vacunacionController.listarMaestroVacunacion();

        //maeVacunacion = vacunacionController.buscarRegistro("1");
        codVacuna = 0; //maeVacunacion.getCatVacunas().getVacunaID();
        codCeaten = 0; //maeVacunacion.getCatCeaten().getCeatenID();
    }

    public List<MaeVacunacion> getLista() {
        return vacunacionController.listarMaestroVacunacion();
    }

    public void setLista(List<MaeVacunacion> lista) {
        this.lista = lista;
    }

    
    
    public List<CatVacunas> getVacunas() {
        return vacunas;
    }

    public MaeVacunacion getMaeVacunacion() {
        return maeVacunacion;
    }

    public int getCodVacuna() {
        return codVacuna;
    }

    public int getCodCeaten() {
        return codCeaten;
    }

    public void setCodCeaten(int codCeaten) {
        this.codCeaten = codCeaten;
    }

    public List<CatCeaten> getCeaten() {
        return ceaten;
    }

    public void setCodVacuna(int codVacuna) {
        this.codVacuna = codVacuna;
    }

    public void setMaeVacunacion(MaeVacunacion maeVacunacion) {
        this.maeVacunacion = maeVacunacion;
    }

    public void setCatVacunas(CatVacunas catVacunas) {
        this.catVacunas = catVacunas;
    }

    public void cmdNuevo() {

        codVacuna = 0;
        codCeaten = 0;

        catVacunas = new CatVacunas();
        catVacunas.setVacunaID(1);

        catCeaten = new CatCeaten();
        catCeaten.setCeatenID(1);

        maeVacunacion = new MaeVacunacion();
        maeVacunacion.setCatCeaten(catCeaten);
        maeVacunacion.setCatVacunas(catVacunas);
        maeVacunacion.setEstadoCivil('S');
      //  addMessage(FacesMessage.SEVERITY_INFO, "Nuevo registro", "Limpiado formulario");
    }

    public void cmdGuardar() {
        MaeVacunacion resultado = new MaeVacunacion();

        catVacunas = new CatVacunas();
        catVacunas.setVacunaID(codVacuna);

        catCeaten = new CatCeaten();
        catCeaten.setCeatenID(codCeaten);

        maeVacunacion.setCatVacunas(catVacunas);
        maeVacunacion.setCatCeaten(catCeaten);

        resultado = vacunacionController.crearRegistro(maeVacunacion);
        addMessage(FacesMessage.SEVERITY_INFO, "Regitro guaradado", "Proceso Existoso");
        cmdNuevo();
        //return "/index";
    }

    public String cmdActualizar() {

        catVacunas = new CatVacunas();
        catVacunas.setVacunaID(codVacuna);

        catCeaten = new CatCeaten();
        catCeaten.setCeatenID(codCeaten);

        //  maeVacunacion = vacunacionController.buscarRegistro("1");
        maeVacunacion.setCatVacunas(catVacunas);
        maeVacunacion.setCatCeaten(catCeaten);

        maeVacunacion = vacunacionController.editarRegistro(maeVacunacion);

        addMessage(FacesMessage.SEVERITY_INFO, "Regitro actulizado", "Proceso Existoso");
        return "/index";
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
