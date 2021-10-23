/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.busquedas;

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
import model.MaeVacunacion;

/**
 *
 * @author AllanRamiro
 */
@Named(value = "buscarPacienteView")
@ViewScoped
public class busquedaPorCadenaStringView implements Serializable {

    boolean snBloquearCmdEditar = true;

    @Inject
    MaestroVacunacionControl vacunacionControl;

    private List<MaeVacunacion> Lista;
    boolean snExisto = false;

    private String var_descripcion = new String();

    @PostConstruct
    public void init() {
        var_descripcion = "";
        this.Lista = new ArrayList<MaeVacunacion>();
    }

    public void cmdBuscarPacientePorNombre() {
        Lista = vacunacionControl.buscarPorDescripcion(var_descripcion);

        if (Lista.size() != 0) {
            addMessage(FacesMessage.SEVERITY_INFO, "Registro encontrado", "Proceso exitoso");
        } else {
            addMessage(FacesMessage.SEVERITY_WARN, "Registro no encontrado", "Fallo de dato");
        }

    }

    public String getVar_descripcion() {
        return var_descripcion;
    }

    public void setVar_descripcion(String var_descripcion) {
        this.var_descripcion = var_descripcion;
    }

    public List<MaeVacunacion> getLista() {
        return Lista;
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void limpiarFormulario() {
        this.Lista = new ArrayList<MaeVacunacion>();
    }

}
