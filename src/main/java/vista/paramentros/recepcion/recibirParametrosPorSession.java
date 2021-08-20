/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.paramentros.recepcion;

import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.SessionMap;
import javax.inject.Named;

/**
 *
 * @author AllanRamiro
 */
@Named
@RequestScoped
public class recibirParametrosPorSession implements Serializable {

    private long id = 0;
    private String descripcion = "";
    private String mensaje = "";

    
    public void onload() {
       ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
       
         Map<String, Object> sessionMap = context.getSessionMap();
         
        String data =context.getSessionMap().get("id").toString();
        if (data != null) {
            id = Long.valueOf(context.getSessionMap().get("id").toString());
            descripcion = String.valueOf(context.getSessionMap().get("descripcion"));
            mensaje = String.valueOf(context.getSessionMap().get("mensaje"));
            sessionMap.remove("id");
             sessionMap.remove("descripcion");
             sessionMap.remove("mensaje");
        }

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
