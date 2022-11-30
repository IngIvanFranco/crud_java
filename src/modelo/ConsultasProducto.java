/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author DIRECCION_TICS
 */
public class ConsultasProducto extends Conex {

    public boolean registrar(producto pro) {

        PreparedStatement ps = null;
        Connection con = getconex();
        String sql = "INSERT INTO producto (codigo,nombre,cantidad,valor) VALUES(?,?,?,?)";

        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setInt(3, pro.getCantidad());
            ps.setDouble(4, pro.getValor());

            ps.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de Insercion " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error de cierre de Conexion" + e.getMessage());
            }
        }
    }

    public boolean modificar(producto pro) {

        PreparedStatement ps = null;
        Connection con = getconex();
        String sql = "UPDATE producto SET codigo = ?, nombre = ?, cantidad = ? , valor = ? WHERE id = ?";

        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setInt(3, pro.getCantidad());
            ps.setDouble(4, pro.getValor());
            ps.setInt(5, pro.getId());

            ps.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de Actualizacion " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error de cierre de Conexion" + e.getMessage());
            }
        }
    }

    public boolean eliminar(producto pro) {

        PreparedStatement ps = null;
        Connection con = getconex();
        String sql = "UPDATE producto SET  estado = '0' WHERE id = ?";

        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, pro.getId());

            ps.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de Actualizacion " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error de cierre de Conexion" + e.getMessage());
            }
        }
    }

    public boolean buscar(producto pro) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getconex();
        String sql = "SELECT * FROM producto WHERE codigo = ? AND estado = 1";

        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());

            rs = ps.executeQuery();

            if (rs.next()) {
                pro.setId(Integer.parseInt(rs.getString("id")));
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                pro.setValor(Double.parseDouble(rs.getString("valor")));
                return true;
            }
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de Actualizacion " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error de cierre de Conexion" + e.getMessage());
            }
        }
    }

    public ResultSet listart() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getconex();
        String sql = "SELECT * FROM producto ";

        try {
            ps = (PreparedStatement) con.prepareStatement(sql);
            rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.print("error al listar ");
            return null;
        }

    }
}
