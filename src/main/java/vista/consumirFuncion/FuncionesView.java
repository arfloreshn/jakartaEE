/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.consumirFuncion;

import controladores.entidades.transaccional.MaestroFacturasControl;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import utilitarios.dbConexcion;

/**
 *
 * @author AllanRamiro
 */
@Named
@ViewScoped
public class FuncionesView implements Serializable{
    
    double  var_x;
    double  var_y;
    double  var_suma;
    
   @Inject
    MaestroFacturasControl facturasControl;
 
   @PostConstruct
   public void init()
   {
       var_x = 49.50;
       var_y = 49.50;
     }
   
     public void cmdFuncionSumar() {
        Connection cn = null;
        try {
           
            cn = dbConexcion.getConectar();
            CallableStatement funcionSumar = cn.prepareCall("{ ? = call fn_sumar(?, ?)  }");
            {
                // segun como este declaro el tipo de variable en la funcion, asi tambien se deben enviar todo los parametros
                funcionSumar.registerOutParameter(1, Types.DOUBLE);
                funcionSumar.setDouble(2, var_x);
                funcionSumar.setDouble(3, var_y);
                funcionSumar.execute();
                var_suma = funcionSumar.getDouble(1);
               
            }
        } catch (com.microsoft.sqlserver.jdbc.SQLServerException ex) {
            System.out.println(ex.getMessage() + " /" + ex.getCause().toString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dbConexcion.close(cn);
        }
    }

    public double getVar_x() {
        return var_x;
    }

    public void setVar_x(double var_x) {
        this.var_x = var_x;
    }

    public double getVar_y() {
        return var_y;
    }

    public void setVar_y(double var_y) {
        this.var_y = var_y;
    }

    public double getVar_suma() {
        return var_suma;
    }

    public void setVar_suma(double var_suma) {
        this.var_suma = var_suma;
    }
     
     
     
}
