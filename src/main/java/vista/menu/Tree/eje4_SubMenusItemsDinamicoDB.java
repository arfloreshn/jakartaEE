/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.menu.Tree;

import controladores.menus.MenuTreeControl;
import java.util.ArrayList;
import java.util.List;
import model.CatMenu;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author AllanRamiro
 */
@Named(value = "SubMenusTreeItemsDinamicoDB")
@RequestScoped
public class eje4_SubMenusItemsDinamicoDB {

    @EJB
    MenuTreeControl servicio_menus;

    private final int var_app_id = 5;
    private TreeNode nodoMenu;
    private TreeNode[] seleccionarNodo;

    @PostConstruct
    public void init() {
        crearMenu();
    }

    public TreeNode getNodoMenu() {
        return nodoMenu;
    }

        
    public TreeNode[] getSeleccionarNodo() {
        return seleccionarNodo;
    }

    public void setSeleccionarNodo(TreeNode[] seleccionarNodo) {
        this.seleccionarNodo = seleccionarNodo;
    }

        public void crearMenu() {

        //Creamos el titulo del menu o bien colocamos el nombre nuestra aplicacion o bien el nombre nuestra empresa
        this.nodoMenu = new DefaultTreeNode("Finanzas", null);
        List<CatMenu> listarSubMenus = servicio_menus.listarSubMenus(var_app_id);

        //Recorremos la lista de los submenus
        for (CatMenu c : listarSubMenus) {
            //Agregamos los submenus de la aplicación o modulo
            TreeNode nodoPadre = new DefaultTreeNode(c.getDescripcion(), nodoMenu);

            List<CatMenu> listarItems = new ArrayList();
            //Cargamos los items de cada submenu
            listarItems = servicio_menus.listarSubMenusItems(var_app_id, c.getItemId());
            for (CatMenu items : listarItems) {
                //Agregamos los hijos o los  items de cada submenu
                nodoPadre.getChildren().add(new DefaultTreeNode(items.getDescripcion()));
            }
        }

    }


    
}
