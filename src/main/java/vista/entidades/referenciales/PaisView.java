/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.entidades.referenciales;

import controladores.entidades.referenciales.CatalogoPaisControl;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.CatPais;

/**
 *
 * @author AllanRamiro
 */
@Named(value="PaisView")
@ViewScoped
public class PaisView implements Serializable {

    boolean snBloquearCmdEditar = true;

    @Inject
    CatalogoPaisControl servicioPais;

    private List<CatPais> Lista;
    private CatPais var_pais;
    boolean snExisto = false;

    private String var_codigoPais = null;

    @PostConstruct
    public void init() {
        this.snExisto = false;
        this.snBloquearCmdEditar = true;
        var_codigoPais = "";
        var_pais = new CatPais();

    }

    public List<CatPais> getLista() {
        return servicioPais.findAll();
    }

    public String cmdGrabarRegistro() {
        var_pais = servicioPais.crearRegistro(var_pais);
        addMessage(FacesMessage.SEVERITY_INFO, "Registro guardado", "Proceso exitoso");
        limpiarFormulario();
        return "/index";

    }

    public void cmdBuscarPaisPorCodigo() {
        this.snBloquearCmdEditar = true;
        var_pais = new CatPais();
        var_pais = servicioPais.buscarRegistro(var_codigoPais);

        if (var_pais != null) {
            this.snBloquearCmdEditar = false;
            addMessage(FacesMessage.SEVERITY_INFO, "Registro encontrado", "Proceso exitoso");
        } else {
            addMessage(FacesMessage.SEVERITY_WARN, "No hay datos", "Error no datos");
        }

    }

    public void cmdEditarRegistro() {
        var_pais = servicioPais.editarRegistro(var_pais);
        addMessage(FacesMessage.SEVERITY_INFO, "Registro actualizado", "Proceso exitoso");
        limpiarFormulario();

    }

    public void cmdBorrarRegistro() {
        this.snExisto = servicioPais.quitarRegistro(var_codigoPais);
        addMessage(FacesMessage.SEVERITY_INFO, "Registro borrado", "Proceso exitoso");
        limpiarFormulario();

    }

    public CatPais getVar_pais() {
        return var_pais;
    }

    public void setVar_pais(CatPais var_pais) {
        this.var_pais = var_pais;
    }

    public String getVar_codigoPais() {
        return var_codigoPais;
    }

    public void setVar_codigoPais(String var_codigoPais) {
        this.var_codigoPais = var_codigoPais;
    }

    public boolean isSnBloquearCmdEditar() {
        return snBloquearCmdEditar;
    }

    public void setSnBloquearCmdEditar(boolean snBloquearCmdEditar) {
        this.snBloquearCmdEditar = snBloquearCmdEditar;
    }

    public void limpiarFormulario() {
        var_pais = new CatPais();
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
