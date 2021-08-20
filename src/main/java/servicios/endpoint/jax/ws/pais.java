/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.endpoint.jax.ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import model.CatPais;

/**
 *
 * @author AllanRamiro
 */
@WebService
//@SOAPBinding(style = Style.RPC)
public interface pais {
        @WebMethod  
        public String saludar(String name);
        
        // Devuelve el listado de paises
        @WebMethod 
        public List<CatPais> ListarPaises();
        
        @WebMethod
        public  CatPais crearPais(CatPais pais);

        @WebMethod
        public  CatPais buscarPais(String id);

         @WebMethod
        public  CatPais editarPais(CatPais pais);

         @WebMethod
        public boolean borrarPais(String id);

        
}
