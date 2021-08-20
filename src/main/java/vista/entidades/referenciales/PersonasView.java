/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.entidades.referenciales;

import controladores.entidades.referenciales.CatalagoPersonasControl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.PersonasMae;

/**
 *
 * @author AllanRamiro
 */
@Named
@ViewScoped
public class PersonasView implements Serializable {
    
    private PersonasMae persona;
    private List<PersonasMae> listadoPersona = new ArrayList<PersonasMae>();
    
    @Inject
    CatalagoPersonasControl personasControl;
    
    @PostConstruct
    public void init() {
    
        listadoPersona = personasControl.ListarTodo();
    }

    public PersonasMae getPersona() {
        return persona;
    }

    public void setPersona(PersonasMae persona) {
        this.persona = persona;
    }

    public List<PersonasMae> getListadoPersona() {
        return listadoPersona;
    }
    
    
    
    
}
