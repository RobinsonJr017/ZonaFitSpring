package gm.zona_fit.gui;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.ClienteServicio;
import gm.zona_fit.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class ZonaFitForma extends JFrame{
    private JPanel panelPrincipal;
    private JTable clientesTabla;
    private JTextField nombreTexto;
    private JTextField apellidoTexto;
    private JTextField membresiaTexto;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JButton limpiarButton;
    IClienteServicio clienteServicio;
    private DefaultTableModel tableModeloCliente;

    @Autowired
    public ZonaFitForma(ClienteServicio clienteServicio){
        this.clienteServicio = clienteServicio;
        iniciarForma();
        guardarButton.addActionListener(e -> guardarCliente());
    }

    private void iniciarForma(){
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null); //Centrar Ventana
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.tableModeloCliente = new DefaultTableModel(0, 4);
        String[] cabaceros = {"ID", "Nombre", "Apellido", "Membresia"};
        this.tableModeloCliente.setColumnIdentifiers(cabaceros);
        this.clientesTabla = new JTable(tableModeloCliente);
        // Cargar Listado de clientes
        listarClientes();

    }

    private void listarClientes(){
        this.tableModeloCliente.setRowCount(0);
        var clientes = this.clienteServicio.listarClientes();
        clientes.forEach(cliente -> {
            Object[] renglonCliente = {
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getMembresia()
            };
            this.tableModeloCliente.addRow(renglonCliente);
        });
    }

    private void guardarCliente(){
        if(nombreTexto.getText().equals("")){
            mostrarMensaje("Proporciona un nombre");
            nombreTexto.requestFocusInWindow();
            return;
        }
        if(membresiaTexto.getText().equals("")){
            mostrarMensaje("Proporciona una Membresia");
            membresiaTexto.requestFocusInWindow();
            return;
        }
        // Recuperamos los valores del formulario
        var nombre = nombreTexto.getText();
        var apellido = apellidoTexto.getText();
        var membresia = Integer.parseInt(membresiaTexto.getText());
        var cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setMembresia(membresia);
        this.clienteServicio.guardarCliente(cliente); // Se inserta
        limpiarFormulario();
        listarClientes();
    }

    private void limpiarFormulario(){
        nombreTexto.setText("");
        apellidoTexto.setText("");
        membresiaTexto.setText("");
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
