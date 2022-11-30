/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author DIRECCION_TICS
 */
public class Conex {

    private final String bd = "pruebajava";
    private final String user = "root";
    private final String pass = "";
    private final String url = "jdbc:mysql://localhost:3306/" + bd;
    private Connection con = null;

    public Connection getconex() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.pass);

        } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "Error de Conexion"+e.getMessage());
        }
        
        return con;
    }
}
