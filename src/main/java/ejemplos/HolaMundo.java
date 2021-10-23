/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplos;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author AllanRamiro
 */

@Named
@RequestScoped
public class HolaMundo {
    
    private String msg = "Hola mundo";

    public String getMsg() {
        return msg;
    }
    
    
}
