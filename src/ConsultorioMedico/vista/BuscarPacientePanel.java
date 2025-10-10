package ConsultorioMedico.vista;

import ConsultorioMedico.util.Estilos;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class BuscarPacientePanel extends JPanel {
    private JTable tablaPacientes;
    private DefaultTableModel modelo;
    private JScrollPane scroll;
    private JButton btnEliminar, btnCancelar, btnBuscar, btnActualizar;
    private JLabel lblDni;
    private JTextField txtDni;
    private String[] columnas;
    private JPanel panelSuperior;
    private JPanel panelBuscar;

    public BuscarPacientePanel() {
        inicializarComponentes();
        configurarLayout();
    }

    private void inicializarComponentes() {
        columnas = new String[]{"DNI", "Nombre", "Apellido", "Teléfono", "Obra Social"};
        lblDni = new JLabel("Buscar por DNI: ");
        txtDni = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnCancelar = new JButton("Cancelar");

        modelo = new DefaultTableModel(columnas, 0);
        tablaPacientes = new JTable(modelo);
        scroll = new JScrollPane(tablaPacientes);
    }

    private void aplicarEstilos() {
        Estilos.aplicarEstiloTextField(txtDni);
        Estilos.aplicarEstiloLabel(lblDni);
        Estilos.aplicarEstiloBoton(btnBuscar, btnCancelar, btnActualizar, btnEliminar);
        Estilos.aplicarEstiloPanel(panelSuperior);
        Estilos.aplicarEstiloTabla(tablaPacientes);
        Estilos.aplicarEstiloScrollPane(scroll);
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(10,10));

        panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,10));

        panelSuperior.add(lblDni);
        panelSuperior.add(txtDni);
        panelSuperior.add(btnBuscar);
        panelSuperior.add(btnActualizar);
        panelSuperior.add(btnEliminar);
        panelSuperior.add(btnCancelar);

        aplicarEstilos();

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }


    // getters
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnCancelar() { return btnCancelar; }
    public JButton getBtnActualizar() { return btnActualizar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JTable getTablaPacientes() { return tablaPacientes; }


    public JTextField getTxtDni() { return txtDni; }
    public String getDni() { return txtDni.getText(); }
    public DefaultTableModel getModelo() { return modelo; }
    // setters


}
