/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AllanRamiro
 */
public class dbConexcion {

    
    public dbConexcion() {
    }

    public static Connection getConectar() {

        Connection con = null;

        if (con != null) {
            return con;
        } else {
            try {
                InputStream inputStream = dbConexcion .class.getClassLoader()
                      .getResourceAsStream("db.properties");
                Properties properties = new Properties();
                if (properties != null) {

                    properties.load(inputStream);
                    String dbDriver = properties.getProperty("dbDriver");
                    String connectionUrl = properties.getProperty("connectionUrl");
                    String user = properties.getProperty("user");
                    String password = properties.getProperty("password");

                    Class.forName(dbDriver);
                    con = DriverManager.getConnection(connectionUrl, user, password);
                }

            } catch (SQLException ex) {
                Logger.getLogger(dbConexcion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(dbConexcion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(dbConexcion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(dbConexcion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return con;
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(dbConexcion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
