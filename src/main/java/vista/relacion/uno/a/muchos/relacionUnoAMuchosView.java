/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.relacion.uno.a.muchos;

import controladores.entidades.referenciales.CatalagoPersonasControl;
import controladores.entidades.referenciales.CatalagoProductosControl;
import controladores.entidades.transaccional.MaestroFacturasControl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Min;
import model.CatProductos;
import model.FacturaDet;
import model.FacturaMae;
import model.PersonasMae;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import vista.entidades.referenciales.ProductosView;

/**
 *
 * @author AllanRamiro
 */
@Named(value = "relacionUnoAMuchosView")
@ViewScoped
public class relacionUnoAMuchosView implements Serializable {

    @Inject
    CatalagoProductosControl productosControl;

    @Inject
    CatalagoPersonasControl personaControl;

    @Inject
    MaestroFacturasControl facturaControl;

    @Inject
    ProductosView helperProductos;

    private String codCliente = "";
    private String codProducto = "";
    private String var_nomCliente = "";
    private String var_descripcion = "";
    private int index;
    FacesContext fc;
    private int rowIndex = 0;

    @Min(value = 1, message = "La cantidad minima a facturar 1")
    private double cantidad = 1;

    @Min(value = 1, message = "No hay items facturados")
    private double subtotal = 0;
    private double itemSubtotal = 0;
    private double impuesto = 0;
    private double gran_total = 0;
    private double sumar = 0;
    private String var_contextPath;

    private PersonasMae persona = new PersonasMae();
    private CatProductos producto = new CatProductos();
    private FacturaMae mae = new FacturaMae();
    private FacturaDet det;
    private List<FacturaDet> itemsFactura;
    private FacturaDet seleccionarItem = new FacturaDet();

    private List<CatProductos> lstproductos = new ArrayList<CatProductos>();
    private List<PersonasMae> lstpersonas = new ArrayList<PersonasMae>();

    @PostConstruct
    public void init() {

        instanciarVariables();
    }

    private void instanciarVariables() {

        this.fc = FacesContext.getCurrentInstance();
        
        this.codCliente = "";
        this.codProducto = "";
        this.var_nomCliente = "";
        this.var_descripcion = "";
        this.subtotal = 0;
        this.impuesto = 0;
        this.gran_total = 0;
        this.var_contextPath = fc.getExternalContext().getApplicationContextPath();

        this.cantidad = 1;

        this.index = 0;

        producto = new CatProductos();
        persona = new PersonasMae();
        mae = new FacturaMae();

        itemsFactura = new ArrayList<FacturaDet>();

        lstproductos = new ArrayList<CatProductos>();
        lstproductos = productosControl.ListarTodo();

        lstpersonas = new ArrayList<PersonasMae>();
        lstpersonas = personaControl.ListarTodo();

    }

    public void cmdQuitarFila() {
        index = itemsFactura.size() - 1;
        this.cantidad = 0;
    }

    public String cmdCrearNuevaFila() {

        boolean sn_valido = true;
        if (this.cantidad == 0) {
            sn_valido = false;
            noItemsFacturados("No ingreso la cantidad a Facturar");
            return null;
        }

        if (this.cantidad <= 0) {
            sn_valido = false;
            noItemsFacturados("Valor no permitido para Facturar");
            return null;
        }

        if (sn_valido == true) {
            det = new FacturaDet();

            instanciarNuevaFila();
            totalizar();
        }

        return null;
    }

    private void instanciarNuevaFila() {

        producto = new CatProductos();
        producto = productosControl.buscarCodBarra(this.codProducto);

        itemSubtotal = 0;
        itemSubtotal = this.cantidad * producto.getCostoVta();
        //;double.valueOf(producto.getCostoVta());

        persona = personaControl.buscarNroDocumento(this.codCliente);
        mae.setFacturaId(null);

        mae.setPersonasMae(persona);
        mae.setNomCliente(persona.getNombres());
        mae.setImpuesto(impuesto);
        mae.setSubtotal(subtotal);
        mae.setTotal(gran_total);

        det.setFacturaMae(mae);
        det.setCatProductos(producto);
        det.setCantidad(this.cantidad);
        det.setPreciovta(producto.getCostoVta());
        det.setSubtotal(itemSubtotal);

        det.setRowIndex(index);

        if (index == 0) {
            itemsFactura.add(index, det);
            itemsFactura.set(index, det);
            itemsFactura.remove(index + 1);

        } else {
            index = itemsFactura.size() - 1;
            itemsFactura.add(index, det);
            itemsFactura.set(index, det);
            itemsFactura.remove(itemsFactura.size() - 1);

        }

        index = +1;
        this.cantidad = 1;

         PrimeFaces.current().focus("form:txtcodProducto");
    }

    private void totalizar() {
        subtotal = 0;

        for (FacturaDet o : itemsFactura) {
            subtotal = subtotal + o.getSubtotal();
        }

        impuesto = subtotal * 0.15;
        gran_total = subtotal + impuesto;
    }

    public String validarCodigoCliente() {
        persona = personaControl.buscarNroDocumento(this.codCliente);
        if (persona == null) {
            this.codCliente = "";
            addMessage(FacesMessage.SEVERITY_WARN, "Codigo no existe", "Revise los datos ingresados" + String.valueOf(this.codCliente));
            PrimeFaces.current().ajax().update("form:txtCodCliente");
            PrimeFaces.current().focus("form:txtCodCliente");
        } else {
            this.var_nomCliente = persona.getNombres() + " " + persona.getPrimerApellido();
             PrimeFaces.current().ajax().update("form:lblnomcliente");
            PrimeFaces.current().focus("form:txtcodProducto");
        }
        return null;
    }

    public String validarCodigoProducto() {
        producto = productosControl.buscarCodBarra(this.codProducto);
        if (producto == null) {
            addMessage(FacesMessage.SEVERITY_WARN, "Codigo no existe ", "Revise los datos ingresados " + String.valueOf(this.codProducto));
            this.codProducto = "";
            PrimeFaces.current().focus("form:txtcodProducto");
        } else {
             this.var_descripcion = producto.getDescripProducto();
             PrimeFaces.current().ajax().update("form:lbldescripcion");
            PrimeFaces.current().focus("form:txtcantidad");
        }
        return null;
    }

    public void cmdProcesarFactura() {

        if (subtotal == 0) {
            noItemsFacturados("No hay items facturados");
            return;
        } else {
            impuesto = subtotal * 0.15;
            gran_total = subtotal + impuesto;

            persona =  personaControl.buscarNroDocumento(this.codCliente);
            mae.setFacturaId(null);
            mae.setPersonasMae(persona);
            mae.setNomCliente(persona.getNombres());
            mae.setImpuesto(impuesto);
            mae.setSubtotal(subtotal);
            mae.setTotal(gran_total);

            mae.setFacturaDetList(itemsFactura);

            FacturaMae obj = new FacturaMae();
            obj = facturaControl.crearRegistro(mae);

            addMessage(FacesMessage.SEVERITY_INFO, "Factura Nro #" + obj.getFacturaId().toString(), "Proceso Existoso");
            instanciarVariables();
            this.codCliente ="";
            this.cantidad = 0;
            this.codProducto = "";
            this.var_descripcion = "";
            this.var_nomCliente = "";        
            PrimeFaces.current().focus("form:txtCodCliente");
            
        }
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getVar_nomCliente() {
        return var_nomCliente;
    }

    public void setVar_nomCliente(String var_nomCliente) {
        this.var_nomCliente = var_nomCliente;
    }

    public String getVar_descripcion() {
        return var_descripcion;
    }

    public void setVar_descripcion(String var_descripcion) {
        this.var_descripcion = var_descripcion;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
        itemsFactura.remove(this.rowIndex);
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getItemSubtotal() {
        return itemSubtotal;
    }

    public void setItemSubtotal(double itemSubtotal) {
        this.itemSubtotal = itemSubtotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getGran_total() {
        return gran_total;
    }

    public void setGran_total(double gran_total) {
        this.gran_total = gran_total;
    }

    public PersonasMae getPersona() {
        return persona;
    }

    public void setPersona(PersonasMae persona) {
        this.persona = persona;
    }

    public CatProductos getProducto() {
        return producto;
    }

    public void setProducto(CatProductos producto) {
        this.producto = producto;
    }

    public FacturaMae getMae() {
        return mae;
    }

    public void setMae(FacturaMae mae) {
        this.mae = mae;
    }

    public FacturaDet getDet() {
        return det;
    }

    public void setDet(FacturaDet det) {
        this.det = det;
    }

    public List<CatProductos> getLstproductos() {
        return lstproductos;
    }

    public void setLstproductos(List<CatProductos> lstproductos) {
        this.lstproductos = lstproductos;
    }

    public List<PersonasMae> getLstpersonas() {
        return lstpersonas;
    }

    public void setLstpersonas(List<PersonasMae> lstpersonas) {
        this.lstpersonas = lstpersonas;
    }

    public List<FacturaDet> getItemsFactura() {
        return itemsFactura;
    }

    public FacturaDet getSeleccionarItem() {
        return seleccionarItem;
    }

    public void setSeleccionarItem(FacturaDet seleccionarItem) {
        this.seleccionarItem = seleccionarItem;
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void noItemsFacturados(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje", mensaje);
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    /*
       Operacion para mostrar la ventana emergente de ayuda de productos
     */
    public void viewHelperProductos() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("width", 800);
        options.put("height", 600);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        // frmProductsoView es un formulario web que debe existir dentro directorio del proceso ejecutado o bien en otro directorio
        PrimeFaces.current().dialog().openDynamic("/subvistas/frmHelperProductosView", options, null);
    }

    /*
       Operacion para cerrar la ventana emergente de ayuda de productos, al cerrar la ventana ayuda de productos
       esta a su vez recibir los datos del formulario externo (GENIAL), despues de cerrar la venta actualizara la descripcion del producto
     */
    public void selecionarProductoDeDialog(CatProductos pro) {
        PrimeFaces.current().dialog().closeDynamic(pro);
    }

    /*
      El evento de actualizar la descripcion del producto y el codigo del producto se realiza despues de cerrarse la ventana emergente 
     */
    public void cmdActualizarLabelDescripcionProducto(SelectEvent<CatProductos> event) {

        CatProductos pro = (CatProductos) event.getObject();
        this.codProducto = pro.getCodBarra();
        this.var_descripcion = pro.getDescripProducto();
        PrimeFaces.current().ajax().update("form:txtcodProducto");
        PrimeFaces.current().ajax().update("form:lbldescripcion");
        //PrimeFaces.current().focus("form:lbldescripcion"); // Hacer un setfocus en el texto box 
    }

    /*
       Operacion para mostrar la ventana emergente de ayuda de personas
     */
    public void viewHelperPersonas() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("width", 800);
        options.put("height", 600);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        // frmHelperPersonasView es un formulario web que debe existir dentro directorio del proceso ejecutado o bien en otro directorio
        PrimeFaces.current().dialog().openDynamic("/subvistas/frmHelperPersonasView", options, null);
    }


    /*
       Operacion para cerrar la ventana emergente de ayuda de personas, al cerrar la ventana ayuda de productos
       esta a su vez recibir los datos del formulario externo (GENIAL), despues de cerrar la venta actualizara la descripcion del producto
     */
    public void selecionarPersonasDeDialog(PersonasMae persona) {
        PrimeFaces.current().dialog().closeDynamic(persona);
    }


    /*
      El evento de actualizar la descripcion del producto y el codigo del producto se realiza despues de cerrarse la ventana emergente 
     */
    public void cmdActualizarLabelDescripcionPersonas(SelectEvent<PersonasMae> event) {

        PersonasMae obj = (PersonasMae) event.getObject();
        this.codCliente = obj.getNroDocumento();
        this.var_nomCliente = obj.getNombres() + obj.getPrimerApellido();
        PrimeFaces.current().ajax().update("form:txtCodCliente");
        PrimeFaces.current().ajax().update("form:lblnomcliente");
        //PrimeFaces.current().focus("form:lbldescripcion"); // Hacer un setfocus en el texto box 
    }

}
