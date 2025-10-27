package ConsultorioMedico.vista.turno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ModificarTurnoPanel extends JPanel {

    private JTable tablaTurnos;
    private DefaultTableModel modeloTabla;
    private JTextField txtBusqueda;
    private JButton btnBuscar;
    private JButton btnVolver;
    private JButton btnLimpiar;
    private JButton btnEliminar;
    private JButton btnMarcarCompletado;
    private JButton btnMarcarCancelado;
    private JLabel lblBusqueda;
    private JLabel lblEstado;
    private JComboBox comboEstado;
    private String[] columnas = {"ID", "Paciente", "Doctor", "Fecha", "Hora", "Estado"};

    public ModificarTurnoPanel() {
        inicializarComponentes();
        configurarLayout();
    }

    private void inicializarComponentes() {
        txtBusqueda = new JTextField(8);

        lblBusqueda = new JLabel("Filtrar por fecha (dd/MM/yyyy):");
        lblEstado = new JLabel("Filtrar por Estado");

        comboEstado = new JComboBox<>(new String[]{"", "Pendiente", "Completado", "Cancelado"});

        btnLimpiar = new JButton("Limpiar");
        btnBuscar = new JButton("Buscar");
        btnEliminar = new JButton("Eliminar turno");
        btnMarcarCompletado = new JButton("Marcar turno completado");
        btnMarcarCancelado = new JButton("Marcar turno cancelado");
        btnVolver = new JButton("Volver");
    }

    private void configurarLayout() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Panel superior con filtros y botones
        JPanel panelSuperior = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        // fila 1 labels y fecha
        gbc.gridx = 0; gbc.gridy = 0;
        panelSuperior.add(lblBusqueda, gbc);
        gbc.gridx = 1;
        panelSuperior.add(txtBusqueda, gbc);

        // label y el comboBox del estado
        gbc.gridx = 0; gbc.gridy = 1;
        panelSuperior.add(lblEstado, gbc);
        gbc.gridx = 1;
        panelSuperior.add(comboEstado, gbc);

        // fila 2, botones de busqueda y boton limpiar
        gbc.gridy = 0;
        gbc.gridx = 3;
        panelSuperior.add(btnBuscar, gbc);
        gbc.gridx = 4;
        panelSuperior.add(btnLimpiar, gbc);
        gbc.gridx = 5;
        panelSuperior.add(btnEliminar, gbc);
        gbc.gridx = 6;
        panelSuperior.add(btnMarcarCompletado, gbc);
        gbc.gridx = 7;
        panelSuperior.add(btnMarcarCancelado, gbc);
        gbc.gridx = 8;
        panelSuperior.add(btnVolver, gbc);

        add(panelSuperior, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaTurnos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaTurnos);
        add(scroll, BorderLayout.CENTER);
    }

    // Getters para el controlador
    public JTable getTablaTurnos() { return tablaTurnos; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
    public JTextField getTxtBusqueda() { return txtBusqueda; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnVolver() { return btnVolver; }
    public JButton getBtnMarcarCancelado() { return btnMarcarCancelado; }
    public JButton getBtnMarcarCompletado() { return btnMarcarCompletado; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JComboBox getComboEstado() { return comboEstado; }
}


