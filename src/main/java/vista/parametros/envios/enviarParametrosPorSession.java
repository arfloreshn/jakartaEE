/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.parametros.envios;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author AllanRamiro
 */
@Named
@ApplicationScoped
public class enviarParametrosPorSession implements Serializable {

    long id;
    String descripcion;
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

         FacesContext context = FacesContext.getCurrentInstance();
         Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        sessionMap.put("id", id);
        sessionMap.put("descripcion", descripcion);
        sessionMap.put("mensaje", mensaje);

       //return "/formulariosConParametros/metodoSession/frmRecibirParametrosSession?faces-redirect=true";

        return "/leccion3/metodoSession/frmRecibirParametrosSession?faces-redirect=false";

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
