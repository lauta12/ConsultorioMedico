package ConsultorioMedico.vista;

//import ConsultorioMedico.util.Estilos;
import javax.swing.*;
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

    public BuscarPacientePanel() {
        inicializarComponentes();
        configurarLayout();
    }

    private void inicializarComponentes() {
        lblDni = new JLabel("Buscar por DNI: ");
        txtDni = new JTextField(20);

        btnBuscar = new JButton("Buscar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnCancelar = new JButton("Cancelar");

        columnas = new String[]{"DNI", "Nombre", "Apellido", "Teléfono", "Obra Social"};
        modelo = new DefaultTableModel(columnas, 0);

        tablaPacientes = new JTable(modelo);
        tablaPacientes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scroll = new JScrollPane(tablaPacientes);
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
