/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.parametros.envios;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author AllanRamiro
 */
@Named
@ViewScoped
public class enviarParametrosPorGet implements Serializable {

    @ManagedProperty(value = "#{param.id}")
    long id;

    @ManagedProperty(value = "#{param.descripcion}")
    String descripcion;

    @ManagedProperty(value = "#{param.mensaje}")
    String mensaje;

    @PostConstruct
    public void init() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            this.mensaje = "";
            this.descripcion = "";
            this.id = 0;
        }
    }

    public String accionEnviarParametros() {

        return "/leccion3/metodoGet/frmRecibirParametros?faces-redirect=true&id="
                + String.valueOf(this.id) + "&descripcion=" + this.descripcion
                + "&mensaje=" + this.mensaje;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
