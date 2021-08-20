/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.menu.Panel;

import controladores.menus.MenuPanelControl;
import java.util.ArrayList;
import java.util.List;
import model.CatMenu;
import org.primefaces.model.menu.DefaultMenuItem;
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
@Named(value = "SubMenusPanelItemsDinamicoDB")
@RequestScoped
public class eje4_SubMenusItemsDinamicoDB {

    @EJB
   MenuPanelControl servicio_menus;

    private MenuModel var_menu_model;
    private DefaultSubMenu var_menu = null;
    private DefaultSubMenu var_submenu = null;
    private DefaultMenuItem var_item = null;
    private final int var_app_id = 5;

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
        var_menu.setLabel("Modulo de Finanzas");

        List<CatMenu> listarSubMenus = servicio_menus.listarSubMenus(var_app_id);

        //Recorremos la lista de los submenus
        for (CatMenu c : listarSubMenus) {
            //Agregamos los submenus de la aplicación o modulo
            var_submenu = new DefaultSubMenu();
            var_submenu.setLabel(c.getDescripcion());
            var_menu.getElements().add(var_submenu);

            List<CatMenu> listarItems = new ArrayList();
            //Cargamos los items de cada submenu
            listarItems = servicio_menus.listarSubMenusItems(var_app_id, c.getItemId());
            for (CatMenu items : listarItems) {
                //Agregamos los items de cada submenu
                var_item = new DefaultMenuItem();
                var_item.setValue(items.getDescripcion());
                var_item.setAjax(false);
                var_item.setIcon("pi pi-file");
                //var_item.setOutcome(items.getRuta()); usaremos outcome para enlanzar nuestras paginas internas
                var_submenu.getElements().add(var_item);

            }
        }

        /*----------------------------------------------------------------------------------------------------------------
            AL CONCLUIR SE DEBEN  AGREGAR LOS SUBMENUS E ITEMS CON LA SIGUIENTE INSTRUCCION
        ------------------------------------------------------------------------------------------------------------------*/
        var_menu_model.getElements().add(var_menu);

    }

}
