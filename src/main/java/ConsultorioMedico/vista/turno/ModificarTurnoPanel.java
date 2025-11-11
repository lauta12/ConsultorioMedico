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
    private JButton btnMarcarPendiente;
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
        btnMarcarPendiente = new JButton("Marcar turno pendiente");
        btnVolver = new JButton("Volver");
    }

    private void configurarLayout() {
        // --- 1. Panel Principal (este panel) ---
        // Usamos BorderLayout y le damos un margen general
        setLayout(new BorderLayout(0, 10)); // 10px de espacio entre el norte y el centro
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 10px de margen en todos los lados


        // --- 2. Panel Superior (para filtros y botones) ---
        // Este panel usará BorderLayout para separar filtros (Oeste) y botones (Este)
        JPanel panelSuperior = new JPanel(new BorderLayout(20, 0)); // 20px de espacio entre ellos
        add(panelSuperior, BorderLayout.NORTH); // Lo ponemos arriba


        // --- 3. Panel de Filtros (Izquierda) ---
        // Usamos GridBagLayout para alinear etiquetas y campos
        JPanel panelFiltros = new JPanel(new GridBagLayout());
        GridBagConstraints gbcFiltros = new GridBagConstraints();
        gbcFiltros.insets = new Insets(5, 5, 5, 5); // Un pequeño margen interno
        gbcFiltros.anchor = GridBagConstraints.WEST; // Alinea todo a la izquierda

        // Fila 0: Filtro Fecha
        gbcFiltros.gridx = 0; gbcFiltros.gridy = 0;
        panelFiltros.add(lblBusqueda, gbcFiltros);

        gbcFiltros.gridx = 1; gbcFiltros.gridy = 0;
        gbcFiltros.fill = GridBagConstraints.HORIZONTAL; // Estira el campo de texto
        panelFiltros.add(txtBusqueda, gbcFiltros);

        // Fila 1: Filtro Estado
        gbcFiltros.gridx = 0; gbcFiltros.gridy = 1;
        gbcFiltros.fill = GridBagConstraints.NONE; // Reseteamos el estiramiento
        panelFiltros.add(lblEstado, gbcFiltros);

        gbcFiltros.gridx = 1; gbcFiltros.gridy = 1;
        gbcFiltros.fill = GridBagConstraints.HORIZONTAL; // Estira el combo
        panelFiltros.add(comboEstado, gbcFiltros);

        // Agregamos los filtros al lado OESTE (izquierda) del panel superior
        panelSuperior.add(panelFiltros, BorderLayout.WEST);


        // --- 4. Panel de Botones (Derecha) ---
        // ¡La clave! Una grilla de 2 filas x 4 columnas.
        // Esto fuerza a TODOS los botones a tener el mismo tamaño y alinearse perfecto.
        JPanel panelBotones = new JPanel(new GridLayout(2, 4, 5, 5)); // 2 filas, 4 cols, 5px de espacio

        // Fila 1 de botones
        panelBotones.add(btnBuscar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVolver);

        // Fila 2 de botones
        panelBotones.add(btnMarcarCompletado);
        panelBotones.add(btnMarcarCancelado);
        panelBotones.add(btnMarcarPendiente);

        // ¡Truco! Agregamos un panel vacío para rellenar el último
        // espacio de la grilla (2da fila, 4ta columna) y que todo quede alineado.
        panelBotones.add(new JPanel());

        // Agregamos los botones al lado ESTE (derecha) del panel superior
        panelSuperior.add(panelBotones, BorderLayout.EAST);


        // --- 5. Tabla (Centro) ---
        // Tu código de la tabla ya estaba perfecto.
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaTurnos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaTurnos);
        add(scroll, BorderLayout.CENTER); // Lo ponemos en el centro
    }


    // Getters para el controlador
    public JTable getTablaTurnos() { return tablaTurnos; }
    public DefaultTableModel getModeloTabla() { return modeloTabla; }
    public JTextField getTxtBusqueda() { return txtBusqueda; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnVolver() { return btnVolver; }
    public JButton getBtnMarcarCancelado() { return btnMarcarCancelado; }
    public JButton getBtnMarcarCompletado() { return btnMarcarCompletado; }
    public JButton getBtnMarcarPendiente() { return btnMarcarPendiente; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JComboBox getComboEstado() { return comboEstado; }
}


