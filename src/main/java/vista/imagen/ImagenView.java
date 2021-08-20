/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.imagen;

import controladores.entidades.referenciales.CatalagoProductosControl;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import model.CatProductos;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author AllanRamiro
 */
@Named
@ViewScoped
public class ImagenView implements Serializable {

    private String var_codProducto = "";
    private String var_descripcion = "";
    private CatProductos producto;

    private String rutaAplicacion;
    private String rutaUploads = "/resources/uploads/";
    private String nombreDestino;
    private UploadedFile file;
    private ServletContext servletContext = null;
    private byte[] var_binario = null;
    private FacesContext facesContext = FacesContext.getCurrentInstance();

    boolean snPostBack;
    @Inject
    CatalagoProductosControl productoControl;

    @PostConstruct
    public void init() {
        // AQUI ES DONDE, VOY A GUARDAR LA 
        this.facesContext = FacesContext.getCurrentInstance();
        // Se instancia servlet para determinar la ruta real de la Aplicacion
        this.rutaAplicacion = this.facesContext.getExternalContext().getApplicationContextPath();
        this.servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        snPostBack =  FacesContext.getCurrentInstance().isPostback();
        if (!snPostBack) {
       instanciar();
        }

    }

    private void instanciar() {
        this.var_codProducto = "";
        this.var_descripcion = "";
        this.nombreDestino = "producto.png";
        this.producto = new CatProductos();

    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;

    }

    public void upload() {

        String archivoOrigen = "";

        if (file != null) {

            archivoOrigen = file.getFileName();

            FacesMessage message = new FacesMessage("Proceso exitoso ", file.getFileName() + " archivo subido.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {

        String archivoOrigen = event.getFile().getFileName();

        if (event.getFile().getFileName() != null) {

            try {

                escribirEnRepositorio(archivoOrigen, event.getFile().getInputStream());
                this.nombreDestino = this.servletContext.getRealPath(this.rutaUploads) + "\\" + archivoOrigen;
                mostrarImagenCargada(archivoOrigen);

                FacesMessage message = new FacesMessage("Proceso exitoso ", archivoOrigen + " esta subido.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }

    /*
      ESCRIBIR ARCHIVOS BINARIOS DENTRO DIRECTORIO DE LA APLICACION
      Ejemplo de escritura archivos binarios, 
      (String) this.servletContext.getRealPath("/resources/docs/" + fileName)
    
      NOTA: IMPORTANTE 
      EL DIRECTORIO DESTINO DEBE EXISITIR Y PARA QUE EXISTA DEBE CONTENER AL MENOS UN ARCHIVO:
     */
    private void escribirEnRepositorio(String fileName, InputStream in) {

        try {

            // AQUI ES DONDE VAMOS A GUARDAR EL ARCHIVO SUBIDO
            String ruta = this.rutaAplicacion;
            this.nombreDestino = this.servletContext.getRealPath(this.rutaUploads) + "\\" + fileName;

            //Borramos el archivo en caso de que exista
            File fichero = new File(this.nombreDestino);
            if (fichero.exists()) {
                fichero.delete();
            }

            //  Escribimos el inputStream  a FileOutputStream
            OutputStream out = new FileOutputStream(new File(this.nombreDestino));
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
                buffer.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            buffer.flush();

            this.var_binario = buffer.toByteArray();

            out.close();
            buffer.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getVar_codProducto() {
        return var_codProducto;
    }

    public void setVar_codProducto(String var_codProducto) {
        this.var_codProducto = var_codProducto;
    }

    public String getVar_descripcion() {
        return var_descripcion;
    }

    public void setVar_descripcion(String var_descripcion) {
        this.var_descripcion = var_descripcion;
    }

    public CatProductos getProducto() {
        return producto;
    }

    public void setProducto(CatProductos producto) {
        this.producto = producto;
    }

    private String mostrarImagenCargada(String filename) {
        this.nombreDestino = filename;
        return filename;
    }

    public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
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
        this.var_codProducto = pro.getCodBarra();
        this.var_descripcion = pro.getDescripProducto();
        this.producto = pro;
        PrimeFaces.current().ajax().update("form:txtcodProducto");
        PrimeFaces.current().ajax().update("form:lbldescripcion");

    }

    public void cmdBuscarProducto() {
        this.producto = productoControl.buscarCodBarra(this.var_codProducto.trim());

        if (this.producto != null) {
            this.var_descripcion = this.producto.getDescripProducto();
            PrimeFaces.current().ajax().update(":form:lbldescripcion");
            PrimeFaces.current().focus(":form:cmdUpload");
        } else {
            FacesMessage message = new FacesMessage("Error ", "Datos no existen" + " Codigo incorrecto");
            FacesContext.getCurrentInstance().addMessage(null, message);
            PrimeFaces.current().focus(":form:txtcodProducto");
        }

    }

    public void cmdGuardar() {
        this.producto = productoControl.buscarCodBarra(this.producto.getCodBarra());
        this.producto.setNomFoto(this.nombreDestino);
        this.producto = productoControl.editarRegistro(producto);
        FacesMessage message = new FacesMessage("Proceso exitoso ", this.producto.getDescripProducto() + " producto actualizado");
        FacesContext.getCurrentInstance().addMessage(null, message);
        instanciar();
        PrimeFaces.current().focus("form:txtcodProducto");
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
