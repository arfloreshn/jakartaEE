/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.menu.Tree;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
 
/**
 *
 * @author AllanRamiro
 */
@Named(value = "SubMenusTreeConItemsEstaticos")
@ViewScoped
public class eje2_SubMenusItemsEstaticos implements Serializable {

    private TreeNode nodoMaestro;
    private TreeNode[] seleccionarNodo;

    
    public eje2_SubMenusItemsEstaticos() {

          this.nodoMaestro = new DefaultTreeNode("Modulo Finanzas", null);
        TreeNode nodoCatalogos = new DefaultTreeNode("Catalogos", nodoMaestro);
        TreeNode nodoActividades = new DefaultTreeNode("Actividades", nodoMaestro);
        TreeNode nodoReportes = new DefaultTreeNode("Reportes", nodoMaestro);

      
       nodoCatalogos.getChildren().add(new DefaultTreeNode("Crear catalogo contable"));
       
        nodoCatalogos.getChildren().add(new DefaultTreeNode("Crear periodos contables"));
        nodoCatalogos.getChildren().add(new DefaultTreeNode("Crear impuestos fiscales"));
        
        nodoActividades.getChildren().add(new DefaultTreeNode("Registrar asientos"));
        nodoActividades.getChildren().add(new DefaultTreeNode("Registrar cambio de divisa"));
        nodoActividades.getChildren().add(new DefaultTreeNode("Realizar cierre mensual"));
        
        nodoReportes.getChildren().add(new DefaultTreeNode("Balance comprobación de saldos"));
        nodoReportes.getChildren().add(new DefaultTreeNode("Balance general financiero"));
        nodoReportes.getChildren().add(new DefaultTreeNode("Estado de Perdidas y Ganancias"));
         
    }

    public TreeNode getNodoMaestro() {
        return nodoMaestro;
    }

    public TreeNode[] getSeleccionarNodo() {
        return seleccionarNodo;
    }

    public void setSeleccionarNodo(TreeNode[] seleccionarNodo) {
        this.seleccionarNodo = seleccionarNodo;
    }

    
    
    

}
