/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios.endpoint.jax.ws;

import controladores.entidades.referenciales.CatalogoPaisControl;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;
import model.CatPais;

/**
 *
 * @author AllanRamiro
 */
@Stateless
@WebService(endpointInterface = "servicios.endpoint.jax.ws.pais")
public class paisImpl implements pais {

    @Inject
    CatalogoPaisControl paisControl;

    private CatPais var_pais;

    private List<CatPais> var_lista = new ArrayList<CatPais>();

    private final String message = "Hola, ";

    public paisImpl() {
    }

    @Override
    public String saludar(String name) {
        return message + name + ".";
    }

    @Override
    public List<CatPais> ListarPaises() {
        var_lista = new ArrayList<>();
        var_lista = paisControl.findAll();
        return var_lista;
    }

    @Override
    public CatPais crearPais(CatPais pais) {
        var_pais = paisControl.crearRegistro(pais);
        return var_pais;
    }

    @Override
    public CatPais buscarPais(String id) {
        var_pais = paisControl.buscarRegistro(id);
        return var_pais;
    }

    @Override
    public CatPais editarPais(CatPais pais) {
        var_pais = paisControl.buscarRegistro(pais.getPaisId().toString());
        var_pais.setDescripPais(pais.getDescripPais());
        var_pais = paisControl.editarRegistro(var_pais);
        return var_pais;
    }

    @Override
    public boolean borrarPais (String id) {
        boolean snExistosa = paisControl.quitarRegistro(id);
        return snExistosa;
    }

}
