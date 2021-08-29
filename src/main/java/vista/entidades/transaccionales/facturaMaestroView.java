/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.entidades.transaccionales;

import controladores.entidades.referenciales.CatalagoPersonasControl;
import controladores.entidades.referenciales.CatalagoProductosControl;
import controladores.entidades.transaccional.MaestroFacturasControl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.CatProductos;
import model.FacturaDet;
import model.FacturaMae;
import model.PersonasMae;

/**
 *
 * @author AllanRamiro
 */
@Named
@ViewScoped
public class facturaMaestroView implements Serializable {

    @Inject
    CatalagoPersonasControl personaControl;

    @Inject
    CatalagoProductosControl productosControl;

    @Inject
    MaestroFacturasControl facturaControl;

    List<PersonasMae> listarPersonas = new ArrayList();
    List<CatProductos> listarProductos = new ArrayList();

    FacturaMae maestroFactura = new FacturaMae();
    FacturaDet detalleFactura = new FacturaDet();

    @PostConstruct
    public void init() {
        listarPersonas = personaControl.ListarTodo();
        listarProductos = productosControl.ListarTodo();
        maestroFactura = new FacturaMae();
        detalleFactura = new FacturaDet();
    }

    public FacturaMae getMaestroFactura() {
        return maestroFactura;
    }

    public void setMaestroFactura(FacturaMae maestroFactura) {
        this.maestroFactura = maestroFactura;
    }

    public FacturaDet getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(FacturaDet detalleFactura) {
        this.detalleFactura = detalleFactura;
    }

    public List<PersonasMae> getListarPersonas() {
        return listarPersonas;
    }

    public List<CatProductos> getListarProductos() {
        return listarProductos;
    }

}
