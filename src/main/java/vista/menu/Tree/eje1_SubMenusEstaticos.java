/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.menu.Tree;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author AllanRamiro
 */
@Named(value = "SubMenuTreeEstatico")
@RequestScoped
public class eje1_SubMenusEstaticos {

    private TreeNode nodoMenu;

    public eje1_SubMenusEstaticos(){
       this.nodoMenu = new DefaultTreeNode("Modulo Finanzas", null);
        TreeNode mnuCatalogos = new DefaultTreeNode("Catalogos", nodoMenu);
        TreeNode mnuActividades = new DefaultTreeNode("Actividades", nodoMenu);
        TreeNode mnuReportes = new DefaultTreeNode("Reportes", nodoMenu);
    } 
    
    

    public TreeNode getNodoMenu() {
        return nodoMenu;
    }
    



    
    
}
