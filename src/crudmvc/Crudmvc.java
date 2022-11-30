/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package crudmvc;

import controlador.CtrProducto;
import java.sql.SQLException;
import modelo.ConsultasProducto;
import modelo.producto;
import vista.fmrProducto;

/**
 *
 * @author DIRECCION_TICS
 */
public class Crudmvc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        producto mod = new producto();
        ConsultasProducto modC = new ConsultasProducto();
        fmrProducto frm = new fmrProducto();
        
        CtrProducto ctrl = new CtrProducto(mod, modC, frm);
        
        ctrl.iniciar();
        frm.setVisible(true);
        
    }
    
}
