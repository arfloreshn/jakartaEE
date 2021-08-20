/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.menu.BreadCrumb;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import static vista.menu.BreadCrumb.MenuBreadCrumbView.OpcionTipo.MenuPanel;
import static vista.menu.BreadCrumb.MenuBreadCrumbView.OpcionTipo.MenuTree;

/**
 *
 * @author AllanRamiro
 */
@Named
@RequestScoped
public class MenuBreadCrumbView {

    private String var_formulario;

    private OpcionTipo var_Tipo;
    private String var_contextPath;
    private String var_enlace;
    private String var_mnu;
    private String var_opt;
    private String var_lbl;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

        var_contextPath = fc.getExternalContext().getApplicationContextPath();
        var_enlace = fc.getExternalContext().getApplicationContextPath() + fc.getViewRoot().getViewId();

        var_lbl = params.get("lbl");
        var_opt = params.get("opt");
        var_mnu = params.get("mnu");

        var_formulario = params.get("frm");

        if (var_opt != null) {
            var_Tipo = OpcionTipo.valueOf(var_opt);
        } else {
            var_Tipo = var_Tipo.Main;
        }
    }

    public MenuModel getBreadCrumbModel() {

        MenuModel model = new DefaultMenuModel();

        /*
     if (var_Tipo == null || var_Tipo == OpcionTipo.Main) {
          return null;
      }*/
        addItemToMenuModel(model, "Home", "/Formularios/index.xhtml", false);

        switch (var_Tipo) {
            case leccion1:
                addItemToMenuModel(model, "Leccion 1",
                        createLink(var_enlace), false);
                addItemToMenuModel(model, var_lbl, createLink(var_enlace), false);
                break;

            case leccion2:
                addItemToMenuModel(model, "Leccion 2",
                        createLink(var_enlace), false);
                addItemToMenuModel(model, var_lbl, createLink(var_enlace), false);
                break;

            case leccion3:
                addItemToMenuModel(model, "Leccion 3",
                        createLink(var_enlace), false);
                addItemToMenuModel(model, var_lbl, createLink(var_enlace), false);

                break;

            case leccion4:
                addItemToMenuModel(model, "Leccion 4",
                        createLink(var_enlace), false);
                addItemToMenuModel(model, var_lbl, createLink(var_enlace), false);
                break;

            case leccion5:
                addItemToMenuModel(model, "Leccion 5",
                        createLink(var_enlace), false);
                addItemToMenuModel(model, var_lbl, createLink(var_enlace), false);
                break;

            case leccion6:
                addItemToMenuModel(model, "Leccion 6",
                        createLink(var_enlace), false);
                addItemToMenuModel(model, var_lbl, createLink(var_enlace), false);

                break;

            case leccion7:
                addItemToMenuModel(model, "Leccion 7",
                        createLink(var_enlace), false);
                addItemToMenuModel(model, var_lbl, createLink(var_enlace), false);
                break;

            case leccion8:
                addItemToMenuModel(model, "Leccion 8",
                        createLink(var_contextPath + "/" + var_opt + "/" + var_mnu + "/" + var_mnu + ".xhtml"), false);
                addItemToMenuModel(model, var_lbl, createLink(var_enlace), true);
                break;

            case leccion9:
                addItemToMenuModel(model, "Leccion 9",
                        createLink(var_contextPath + "/" + var_opt + "/" + var_mnu + "/" + var_mnu + ".xhtml"), false);
                addItemToMenuModel(model, var_lbl, createLink(var_enlace), true);
                break;

            case leccion_10:
                addItemToMenuModel(model, "Leccion 10",
                        createLink(var_contextPath + "/" + var_opt + "/" + var_mnu + "/" + var_mnu + ".xhtml"), false);
                addItemToMenuModel(model, var_lbl, createLink(var_enlace), true);
                break;

            case leccion_11:
                addItemToMenuModel(model, "Leccion 11",
                        createLink(var_contextPath +"/"+ var_mnu + "/frmSOA.xhtml"), false);
                addItemToMenuModel(model, var_lbl, createLink(var_enlace), true);
                break;

            case leccion_12:
                addItemToMenuModel(model, "Leccion 12",
                        createLink(var_contextPath + "/" + var_opt + "/" + var_mnu + "/" + var_mnu + ".xhtml"), false);
                addItemToMenuModel(model, var_lbl, createLink(var_enlace), true);
                break;

            case MenuPanel:
                addItemToMenuModel(model, "Leccion 8", createLink(var_enlace),
                        false);
                addItemToMenuModel(model, var_lbl, createLink(var_enlace), true);
                break;

            case MenuTree:
                addItemToMenuModel(model, "Leccion 8", createLink(var_enlace),
                        false);
                addItemToMenuModel(model, var_lbl, createLink(var_enlace), false);
                break;
        }

        return model;
    }

    public String getVar_formulario() {
        return var_opt;
    }

    public void setVar_formulario(String var_opt) {
        this.var_opt = var_opt;
    }

    public OpcionTipo getVar_menu() {
        return var_Tipo;
    }

    public void setVar_menu(OpcionTipo var_Tipo) {
        this.var_Tipo = var_Tipo;
    }

    private void addItemToMenuModel(MenuModel model, String value, String url, boolean disabled) {
        DefaultMenuItem element = new DefaultMenuItem();
        element.setValue(value);
        element.setUrl(url);
        element.setDisabled(disabled);
        model.getElements().add(element);
    }

    private String createLink(String formulario) {
        return String.format(formulario + "?frm=%s&mnu=%s&opt=%s&lbl=%s", var_mnu, var_mnu, var_opt, var_lbl);
    }

    public enum OpcionTipo {
        Main,
        leccion1,
        leccion2,
        leccion3,
        leccion4,
        leccion5,
        leccion6,
        leccion7,
        leccion8,
        leccion9,
        leccion_10,
        leccion_11,
        leccion_12,
        MenuPanel,
        MenuTree;
    }

}
