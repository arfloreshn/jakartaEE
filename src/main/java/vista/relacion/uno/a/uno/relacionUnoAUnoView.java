/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.relacion.uno.a.uno;

import controladores.entidades.referenciales.CatalagoPersonasControl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.PersonasDireccion;
import model.PersonasMae;

/**
 *
 * @author AllanRamiro
 */
@Named
@ViewScoped
public class relacionUnoAUnoView implements Serializable {

    @Inject
    CatalagoPersonasControl personaControl;

    PersonasMae persona = new PersonasMae();
    PersonasDireccion direccion = new PersonasDireccion();

    @PostConstruct
    public void init() {
        persona = new PersonasMae();
        direccion = new PersonasDireccion();
    }

    public PersonasMae getPersona() {
        return persona;
    }

    public void setPersona(PersonasMae persona) {
        this.persona = persona;
    }

    public PersonasDireccion getDireccion() {
        return direccion;
    }

    public void setDireccion(PersonasDireccion direccion) {
        this.direccion = direccion;
    }

    public void cmdCrearRegistro() {

        PersonasMae obj = new PersonasMae();
        obj = personaControl.crearRegistro(persona,direccion);

        if (persona == null) {
            addMessage(FacesMessage.SEVERITY_FATAL, "Error", "Fallo");
        }
        {
        
            addMessage(FacesMessage.SEVERITY_INFO, "Registro creado \n" , "Proceso Existoso");
        persona = new PersonasMae();
        direccion = new PersonasDireccion();
            
        }

    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
