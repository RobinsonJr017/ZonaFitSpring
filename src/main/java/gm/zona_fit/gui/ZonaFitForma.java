package gm.zona_fit.gui;

import gm.zona_fit.servicio.ClienteServicio;
import gm.zona_fit.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@Component
public class ZonaFitForma extends JFrame{
    private JPanel panelPrincipal;
    private JTable clientesTabla;
    IClienteServicio clienteServicio;
    private DefaultTableModel tableModeloCliente;

    @Autowired
    public ZonaFitForma(ClienteServicio clienteServicio){
        this.clienteServicio = clienteServicio;
        iniciarForma();
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
}
