/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.busquedas;

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
@Named(value="buscarEntero")
@ViewScoped
public class busquedaPorCodigoView implements Serializable {

    boolean snBloquearCmdEditar = true;

    @Inject
    CatalogoPaisControl servicioPais;

    private List<CatPais> Lista;
    private CatPais var_pais;
    private CatPais var_data;
    boolean snExisto = false;

    private String var_codigoPais = null;

    @PostConstruct
    public void init() {
        this.snExisto = false;
        this.snBloquearCmdEditar = true;
        var_codigoPais = "";
        var_pais = new CatPais();
        var_data = new CatPais();

    }

    public void cmdBuscarPorCodigo() {
        this.snBloquearCmdEditar = true;
        var_pais = new CatPais();
        var_pais = servicioPais.buscarRegistro(var_codigoPais);

        if (var_pais != null) {
            this.snBloquearCmdEditar = false;
            addMessage(FacesMessage.SEVERITY_INFO, "Registro encontrado", "Proceso exitoso");
        } else {
            addMessage(FacesMessage.SEVERITY_WARN, "Registro no encontrado", "Fallo de dato");
        }

    }

    public String getVar_codigoPais() {
        return var_codigoPais;
    }

    public void setVar_codigoPais(String var_codigoPais) {
        this.var_codigoPais = var_codigoPais;
    }


    public void limpiarFormulario() {
        var_pais = new CatPais();
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
