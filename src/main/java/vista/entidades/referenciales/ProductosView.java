/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.entidades.referenciales;

import controladores.entidades.referenciales.CatalagoProductosControl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.CatProductos;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author AllanRamiro
 */
@Named
@ViewScoped
public class ProductosView implements Serializable {

    private List<CatProductos> listaproductos = new ArrayList<CatProductos>();
    private CatProductos productoEscogido;

    @Inject
    private CatalagoProductosControl productosControl;

    @PostConstruct
    public void init() {

        this.listaproductos = new ArrayList<CatProductos>();
        this.listaproductos = productosControl.ListarTodo();
        this.productoEscogido = new CatProductos();

    }

    public List<CatProductos> getListaproductos() {
        return listaproductos;
    }

    public void setListaproductos(List<CatProductos> listaproductos) {
        this.listaproductos = listaproductos;
    }

    public CatProductos getProductoEscogido() {
        return productoEscogido;
    }

    public void setProductoEscogido(CatProductos productoEscogido) {
        this.productoEscogido = productoEscogido;
    }

    public void cmdEscogerProductoYCerrarVentana(SelectEvent event) {
        CatProductos pro = (CatProductos) event.getObject();
        PrimeFaces.current().dialog().closeDynamic(pro);
    }
}
