package com.redv.com.ESports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    public Connection conectar() {

        Connection conn = null;

        try

        {
            // Cargar el driver Oracle JDBC
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            // Cadena de conexión: driver@machineName:port:SID, userid, password
            conn = DriverManager.getConnection("jdbc:oracle:thin:@10.10.10.9:1521:db12102", "system", "oracle");
            System.out.println("INFO: Conexión abierta");

        } catch (SQLException ex) {
            System.out.println(ConexionBD.class.getName() + " .conectar() " + ex);
        }

        return conn;
    }

    public void desconectar(Connection conn) {

        // Cerrar la conexión
        System.out.println("--- Desconexión de Oracle ----------------------");
        try {
            conn.close();
            System.out.println("INFO: Conexión cerrada");
        } catch (SQLException ex) {
            System.out.println(ConexionBD.class.getName() + " .desconectar() " + ex);
        }
        System.out.println("------------------------------------------------");
    }

}
