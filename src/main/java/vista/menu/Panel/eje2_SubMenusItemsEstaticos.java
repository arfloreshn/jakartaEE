/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.menu.Panel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author AllanRamiro
 */
@Named(value = "SubMenusPanelConItemsEstaticos")
@RequestScoped
public class eje2_SubMenusItemsEstaticos {

    private MenuModel var_menu_model;
    private DefaultSubMenu var_menu = new DefaultSubMenu();
    private DefaultSubMenu var_submenu = new DefaultSubMenu();
    private DefaultMenuItem var_item = null;

    @PostConstruct
    public void init() {
        crearMenu();
    }

    public MenuModel getVar_menu_model() {
        return var_menu_model;
    }

    public void setVar_menu_model(MenuModel var_menu_model) {
        this.var_menu_model = var_menu_model;
    }

    public void crearMenu() {

        var_menu_model = new DefaultMenuModel();

        //Creamos el titulo del menu o bien colocamos el nombre nuestra aplicacion o bien el nombre nuestra empresa
        var_menu = new DefaultSubMenu();
        var_menu.setLabel("Modulo Finanzas");

        /*-----------------------------------------------------------------------------------------------------------------------------
                                                                              Agregamos el primer submenu
        -------------------------------------------------------------------------------------------------------------------------------*/
        var_submenu = new DefaultSubMenu();
        var_submenu.setLabel("Catalogos");
        var_menu.getElements().add(var_submenu);

        //Agregamos el primer item submenu1
        var_item = new DefaultMenuItem();
        var_item.setValue("Periodos Contables");
        var_item.setAjax(false);
        var_item.setIcon("pi pi-file");
        var_submenu.getElements().add(var_item);

        //Agregamos el 2do item al submenu1
        var_item = new DefaultMenuItem();
        var_item.setValue("Impuesto Fiscales");
        var_item.setAjax(false);
        var_item.setIcon("pi pi-file");
        var_submenu.getElements().add(var_item);

        /* -------------------------------------------------------------------------------------------------------------
                                                                         Agregamos el 2do submenu
         --------------------------------------------------------------------------------------------------------------*/
        var_submenu = new DefaultSubMenu();
        var_submenu.setLabel("Actividades");
        var_menu.getElements().add(var_submenu);

        //Agregamos el primer item del submenu2
        var_item = new DefaultMenuItem();
        var_item.setValue("Asientos de diario");
        var_item.setAjax(false);
        var_item.setIcon("pi pi-file");
        var_submenu.getElements().add(var_item);

        //Agregamos el 2do item al del submenu2
        var_item = new DefaultMenuItem();
        var_item.setValue("Asientos de ajuste");
        var_item.setAjax(false);
        var_item.setIcon("pi pi-file");
        var_submenu.getElements().add(var_item);

        /*----------------------------------------------------------------------------------------------------------------------
                        Para concluir agregamos los submenus,y los items de los submenus al menu principal
         ------------------------------------------------------------------------------------------------------------------------*/
        var_menu_model.getElements().add(var_menu);

    }

}
