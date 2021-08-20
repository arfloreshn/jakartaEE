/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.consumirSP;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import utilitarios.dbConexcion;

/**
 *
 * @author AllanRamiro
 */
@Named
@ViewScoped
public class StoreProcedureView implements Serializable {

    Date fec_desde;
    Date fec_hasta;
    double var_suma;

    @PostConstruct
    public void init() {
        fec_desde = new Date("1974/01/01");
        fec_hasta = new Date("1974/12/31");
        //var_suma = 0;
    }

    public Date getFec_desde() {
        return fec_desde;
    }

    public void setFec_desde(Date fec_desde) {
        this.fec_desde = fec_desde;
    }

    public Date getFec_hasta() {
        return fec_hasta;
    }

    public void setFec_hasta(Date fec_hasta) {
        this.fec_hasta = fec_hasta;
    }

    public double getVar_suma() {
        return var_suma;
    }

    public void setVar_suma(double var_suma) {
        this.var_suma = var_suma;
    }

    public void cmdContarPersonas() {
        Connection cn = null;
        try {

            cn = dbConexcion.getConectar();
            CallableStatement contarPersonas = cn.prepareCall("{? = call sp_personas_contarPorRangoFechas(?, ?, ?)  }");
            {
                
                java.sql.Date var_fec_desde = convertir(fec_desde);
                java.sql.Date var_fec_hasta = convertir(fec_hasta);
     
                // segun como este declaro el tipo de variable en la funcion, asi tambien se deben enviar todo los parametros
                contarPersonas.registerOutParameter(1, Types.VARCHAR); // El primer parametro lee el estado del procedimiento almacenado, si fue exitoso o no
                contarPersonas.registerOutParameter(4, Types.DOUBLE); // El cuarto valor lee los datos devueltos del procedimiento almacenado
                contarPersonas.setDate(2, var_fec_desde);
                contarPersonas.setDate(3, var_fec_hasta);
                contarPersonas.executeUpdate();
                var_suma = contarPersonas.getDouble(4);

            }
        } catch (com.microsoft.sqlserver.jdbc.SQLServerException ex) {
            System.out.println(ex.getMessage() + " /" + ex.getCause().toString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dbConexcion.close(cn);
        }
    }

    private static java.sql.Date convertir(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

}
