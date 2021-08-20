/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.busquedas;

import controladores.entidades.referenciales.CatalogoPaisControl;
import controladores.entidades.transaccional.MaestroVacunacionControl;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import static java.util.Date.parse;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.CatPais;
import model.MaeVacunacion;

/**
 *
 * @author AllanRamiro
 */
@Named(value = "buscarFechas")
@RequestScoped
public class busquedaPorFechaView implements Serializable {

    @Inject
    MaestroVacunacionControl vacunacionControl;

    private List<MaeVacunacion> Lista;
   
   
     
    private Date var_fecDesde =Calendar.getInstance().getTime();   
    private Date var_fecHasta = Calendar.getInstance().getTime();   

    @PostConstruct
    public void init() {
        
//El calendario en formato UK o Britanico, devuelva la fecha en dd/mm/yyyy.
        
        GregorianCalendar calendario = new GregorianCalendar(Locale.UK);
        this.var_fecDesde = calendario.getTime();   
        this.var_fecHasta = calendario.getTime();   
        
        this.Lista = new ArrayList<MaeVacunacion>();
    }

    public Date getVar_fecDesde() {
        return var_fecDesde;
    }

    public void setVar_fecDesde(Date var_fecDesde) {
        this.var_fecDesde = var_fecDesde;
    }

    public Date getVar_fecHasta() {
        return var_fecHasta;
    }

    public void setVar_fecHasta(Date var_fecHasta) {
        this.var_fecHasta = var_fecHasta;
    }

    public List<MaeVacunacion> getLista() {
        return Lista;
    }

    public void setLista(List<MaeVacunacion> Lista) {
        this.Lista = Lista;
    }

    
    
    
    public void cmdListarPorRangoFechas() {

            // hay que convertir de formato UK (Britanico) a --> Norte Americano o EEUU, para evitar perder registros de busqueda
   
              DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

            Date fec_ini = new Date(df.format(var_fecDesde));
            Date fec_fin = new Date(df.format(var_fecHasta));
       
            Lista = vacunacionControl.listarRangoFechas(fec_ini, fec_fin);

            if (Lista.size() == 0) {
                addMessage(FacesMessage.SEVERITY_WARN, "Seleccione otro rango", "Error - no hay datos");
            } else {
                addMessage(FacesMessage.SEVERITY_INFO, "Registro  encontrados " + Lista.size(), "Proceso exitoso");
            }


    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

}
