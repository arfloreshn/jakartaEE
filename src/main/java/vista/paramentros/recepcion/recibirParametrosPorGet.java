/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.paramentros.recepcion;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author AllanRamiro
 */
@Named
@RequestScoped
public class recibirParametrosPorGet  implements Serializable {

    private long id=0;
    private String descripcion="";
    private String mensaje="";

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        id = Long.valueOf(params.get("id"));
        descripcion = params.get("descripcion");
        mensaje = params.get("mensaje");

        /*
        id = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        descripcion = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("descripcion"));
        mensaje = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mensaje"));
         */
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
