/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.consumir.jax.ws;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import model.CatPais;
import servicios.endpoint.jax.ws.pais;

/**
 *
 * @author AllanRamiro
 */
@Named
@SessionScoped
public class ConsumirWebService implements Serializable {

    URL wsdlURL;
    QName nombre_servicio;
    Service service;
    pais cliente; //El cliente es la interface del webservice creado
   
    
    private String nombre = "";
    private String respuesta = "";
    private List<CatPais> listarPaises = new ArrayList<CatPais>();

    @PostConstruct
    public void init() {
        nombre = "";
        respuesta = "Iniciando";
        try {
            // Payara this.wsdlURL = new URL("http://localhost:8080/paisImplService/paisImpl?wsdl");
            this.wsdlURL = new URL("http://localhost:8080/jakartaEE/paisImpl?wsdl");
            this.nombre_servicio = new QName("http://ws.jax.endpoint.servicios/", "paisImplService");
            this.service = Service.create(this.wsdlURL, this.nombre_servicio);

            cliente = service.getPort(pais.class);

        } catch (MalformedURLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void cmdSaludar() {

        try {

            respuesta = "Iniciando Test";
            respuesta = cliente.saludar(nombre);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void cmdListarPaises() {

        try {

            respuesta = "Iniciando Listado";

            listarPaises = new ArrayList<>();
            listarPaises = cliente.ListarPaises();

            respuesta = "Prueba exitosa";

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public List<CatPais> getListarPaises() {
        return listarPaises;
    }

}
