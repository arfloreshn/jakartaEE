/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.menu.Panel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author AllanRamiro
 */
@Named(value="SubMenuPanelEstatico")
@RequestScoped
public class eje1_SubMenusEstaticos {

    private MenuModel var_menu_model;
    private DefaultSubMenu var_menu = null;
    private DefaultSubMenu var_submenu = null;
    
    @PostConstruct
    public void init() {
        var_menu_model = new DefaultMenuModel();

        //Creamos el titulo del menu o bien colocamos el nombre nuestra aplicacion o bien el nombre nuestra empresa
        var_menu = new DefaultSubMenu();
        var_menu.setLabel("Modulo Finanzas");

        //Agregamos el primer submenu
        var_submenu = new DefaultSubMenu();
        var_submenu.setLabel("Catalagos");

        var_menu.getElements().add(var_submenu);

           //Agregamos el 2do submenu
       
        var_submenu = new DefaultSubMenu();
        var_submenu.setLabel("Actividades");

        var_menu.getElements().add(var_submenu);

        // Para concluir agregamos tanto los submenus, como los items de los submenus al menu principal
        var_menu_model.getElements().add(var_menu);

    }

    public MenuModel getVar_menu_model() {
        return var_menu_model;
    }

    public void setVar_menu_model(MenuModel var_menu_model) {
        this.var_menu_model = var_menu_model;
    }

}
