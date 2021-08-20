/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.menus;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.CatMenu;

/**
 *
 * @author AllanRamiro
 */
@Stateless
public class MenuTreeControl {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("dbLeccionesPU");
    EntityManager em = emf.createEntityManager();

      public List<CatMenu> listarSubMenus(int appID) {
        List<CatMenu> lstsubMenu = new ArrayList();

        Query submnuQry = em.createNativeQuery("{call get_submenu(?)}", CatMenu.class)
                .setParameter(1, appID);
        lstsubMenu = submnuQry.getResultList();

        return lstsubMenu;
    }

    public List<CatMenu> listarSubMenusItems(int appID, int itemSubMenu) {
        List<CatMenu> lstitems = new ArrayList();

        //Cargamos los items de cada submodulo
        Query items = em.createNativeQuery("{call get_submenu_items(?,?)}", CatMenu.class)
                .setParameter(1, appID)
                .setParameter(2, itemSubMenu);

        lstitems = items.getResultList();

        return lstitems;
    }

}
