/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ConsultasProducto;
import modelo.producto;
import vista.fmrProducto;

/**
 *
 * @author DIRECCION_TICS
 */
public class CtrProducto implements ActionListener {

    private producto mod;
    private ConsultasProducto modC;
    private fmrProducto frm;
    private ResultSet rs;

    public CtrProducto(producto mod, ConsultasProducto modC, fmrProducto frm) {
        this.frm = frm;
        this.mod = mod;
        this.modC = modC;
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);

    }

    public void iniciar() throws SQLException {
        frm.setTitle("Productos");
        frm.setLocationRelativeTo(null);
        frm.txtId.setVisible(false);
        cargartabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnGuardar) {

            mod.setCodigo(frm.txtCodigo.getText());
            mod.setNombre(frm.txtNombre.getText());
            mod.setCantidad(Integer.parseInt(frm.txtCantidad.getText()));
            mod.setValor(Double.parseDouble(frm.txtPrecio.getText()));

            if (modC.registrar(mod)) {
                JOptionPane.showMessageDialog(null, "Registro exitoso");
                limpiar();
                try {
                    cargartabla();
                } catch (SQLException ex) {
                    Logger.getLogger(CtrProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.print("error");
                limpiar();
                try {
                    cargartabla();
                } catch (SQLException ex) {
                    Logger.getLogger(CtrProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        if (e.getSource() == frm.btnModificar) {

            mod.setId(Integer.parseInt(frm.txtId.getText()));
            mod.setCodigo(frm.txtCodigo.getText());
            mod.setNombre(frm.txtNombre.getText());
            mod.setCantidad(Integer.parseInt(frm.txtCantidad.getText()));
            mod.setValor(Double.parseDouble(frm.txtPrecio.getText()));

            if (modC.modificar(mod)) {
                JOptionPane.showMessageDialog(null, "se edito exitosamente el producto");
                limpiar();
                try {
                    cargartabla();
                } catch (SQLException ex) {
                    Logger.getLogger(CtrProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.print("error");
                limpiar();
                try {
                    cargartabla();
                } catch (SQLException ex) {
                    Logger.getLogger(CtrProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        if (e.getSource() == frm.btnEliminar) {

            mod.setId(Integer.parseInt(frm.txtId.getText()));

            if (modC.eliminar(mod)) {
                JOptionPane.showMessageDialog(null, "se elimino exitosamente el producto");
                limpiar();
                try {
                    cargartabla();
                } catch (SQLException ex) {
                    Logger.getLogger(CtrProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.print("error");
                limpiar();
                try {
                    cargartabla();
                } catch (SQLException ex) {
                    Logger.getLogger(CtrProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        if (e.getSource() == frm.btnBuscar) {

            mod.setCodigo(frm.txtCodigo.getText());

            if (modC.buscar(mod)) {
                frm.txtId.setText(String.valueOf(mod.getId()));
                frm.txtCodigo.setText(mod.getCodigo());
                frm.txtNombre.setText(mod.getNombre());
                frm.txtCantidad.setText(String.valueOf(mod.getCantidad()));
                frm.txtPrecio.setText(String.valueOf(mod.getValor()));

            } else {
                JOptionPane.showMessageDialog(null, "no se encontraron datos");
                limpiar();
            }

        }
        if (e.getSource() == frm.btnLimpiar) {
            limpiar();
        }

    }

    public void limpiar() {
        frm.txtCantidad.setText(null);
        frm.txtCodigo.setText(null);
        frm.txtId.setText(null);
        frm.txtNombre.setText(null);
        frm.txtPrecio.setText(null);
    }

    public void cargartabla() throws SQLException {
        String status = "";
        String[] titulos = {"Id", "Code", "Name", "Qty", "Price", "Status"};
        String[] registros = new String[6];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        rs = modC.listart();
        while (rs.next()) {
            registros[0] = rs.getString("id");
            registros[1] = rs.getString("codigo");
            registros[2] = rs.getString("nombre");
            registros[3] = rs.getString("cantidad");
            registros[4] = rs.getString("valor");
            if (Integer.parseInt(rs.getString("estado")) == 1) {
                status = "Activo";
            } else {
                status = "Inactivo";
            }
            registros[5] = status;

            modelo.addRow(registros);
        }

        frm.table.setModel(modelo);
    }

}
