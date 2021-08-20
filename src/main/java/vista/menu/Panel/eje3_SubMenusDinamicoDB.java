/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.menu.Panel;

import controladores.menus.MenuPanelControl;
import java.io.Serializable;
import java.util.List;
import model.CatMenu;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author AllanRamiro
 */
@Named(value = "SubMenusPanelDinamicoDB")
@RequestScoped
public class eje3_SubMenusDinamicoDB implements Serializable {

    @EJB
    MenuPanelControl servicio_menus;

    private MenuModel var_menu_model;
    private DefaultSubMenu var_menu = null;
    private DefaultSubMenu var_submenu = null;

    @PostConstruct
    public void init() {
        var_menu_model = new DefaultMenuModel();

        //Creamos el titulo del menu o bien colocamos el nombre nuestra aplicacion o bien el nombre nuestra empresa
        var_menu = new DefaultSubMenu();
        var_menu.setLabel("Modulo de Finanzas");

        List<CatMenu> lista = servicio_menus.listarSubMenus(5);

        //Recorremos la lista de los submenus
        for (CatMenu c : lista) {
            //Agregamos los submenus de la aplicación o modulo
            var_submenu = new DefaultSubMenu();
            var_submenu.setLabel(c.getDescripcion());
            var_menu.getElements().add(var_submenu);
        }

        /*----------------------------------------------------------------------------------------------------------------
            AL CONCLUIR SE DEBE DE AGREGAR SUBMENUS E ITEMS CON LA SIGUIENTE INSTRUCCION
        ------------------------------------------------------------------------------------------------------------------*/
        var_menu_model.getElements().add(var_menu);

    }

    public MenuModel getVar_menu_model() {
        return var_menu_model;
    }

    public void setVar_menu_model(MenuModel var_menu_model) {
        this.var_menu_model = var_menu_model;
    }

}
