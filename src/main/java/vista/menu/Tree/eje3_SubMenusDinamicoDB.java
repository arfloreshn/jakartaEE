/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.menu.Tree;

import controladores.menus.MenuTreeControl;
import java.io.Serializable;
import java.util.List;
import model.CatMenu;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author AllanRamiro
 */
@Named(value = "SubMenusTreeDinamicoDB")
@ViewScoped
public class eje3_SubMenusDinamicoDB implements Serializable {

    @EJB
    MenuTreeControl servicio_menus;

    private TreeNode nodoMenu;
   
    @PostConstruct
    public void init() {
  
    //Creamos el titulo del menu o bien colocamos el nombre nuestra aplicacion o bien el nombre nuestra empresa
    this.nodoMenu = new DefaultTreeNode("Finanzas", null);
    
       List<CatMenu> lista = servicio_menus.listarSubMenus(5);
    
    //Recorremos la lista de los submenus
    for (CatMenu c : lista ) {
            //Agregamos los submenus de la aplicación o modulo
            TreeNode obj = new DefaultTreeNode(c.getDescripcion(), nodoMenu);
    }

}

    public TreeNode getNodoMenu() {
        return nodoMenu;
    }
    
}