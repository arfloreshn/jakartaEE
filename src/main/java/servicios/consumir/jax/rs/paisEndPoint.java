/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.consumir.jax.rs;

import controladores.entidades.referenciales.CatalogoPaisControl;
import java.net.URI;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import model.CatPais;


/**
 * @author: Allan Ramiro Flores Murillo
 * Nacionalidad: Hondure�a
 * Fecha creacion: 23/07/2020
 * email: arfloreshn@gmail.com
 */

@Path("/pais")
@Consumes("application/json")
@Produces("application/json")
public class paisEndPoint {
    
    @EJB
    CatalogoPaisControl paisServicio;
    
    private CatPais respuesta = null;
    
    @POST
    public Response crearRegistro(CatPais entity, @Context UriInfo uriInfo) {
        
      
       this.respuesta = paisServicio.crearRegistro(entity);
        
        URI uri = uriInfo.getAbsolutePathBuilder().build();
                
        return Response.status(Response.Status.CREATED)
                .location(uri)
                .entity(respuesta)
                .build();
    }

    @PUT
    @Path("{id}")
    public Response editRegistro(@PathParam("id") Integer id, CatPais entity, @Context UriInfo uriInfo) 
    {
        
        CatPais pais = paisServicio.buscarRegistro(String.valueOf(id));
        pais.setDescripPais(entity.getDescripPais());
        
        this.respuesta = paisServicio.editarRegistro(pais);
        
         URI uri = uriInfo.getAbsolutePathBuilder().build();
          
          return Response.status(Response.Status.OK)
                .location(uri)
                .entity(respuesta)
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminarRegistro(@PathParam("id") Integer id,@Context UriInfo uriInfo) 
    {
      URI uri = uriInfo.getAbsolutePathBuilder().build();
      paisServicio.quitarRegistro(String.valueOf(id));
      return Response.status(200)
              .location(uri)
              .entity("Registro Eliminado").build();
       
    }

    @GET
    public List<CatPais> listarRegistros() {
        return paisServicio.ListarTodo();
    }

    @GET
    @Path("{id}")
    public CatPais buscarRegistro_x_id(@PathParam("id") String id) {
        return paisServicio.buscarRegistro(id);
    }

    
}
