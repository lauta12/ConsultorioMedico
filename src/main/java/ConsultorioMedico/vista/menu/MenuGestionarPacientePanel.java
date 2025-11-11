package ConsultorioMedico.vista.menu;

import ConsultorioMedico.util.Estilos;

import javax.swing.*;
import java.awt.*;

public class MenuGestionarPacientePanel extends JPanel {
    private JButton btnRegistarPaciente;
    private JButton btnModificarPaciente;
    private JButton btnVolver;
    private JLabel lblTitulo;

    public MenuGestionarPacientePanel() {
        inicializarComponentes();
        configurarLayout();
    }

    private void inicializarComponentes() {
        lblTitulo = new JLabel("Gestión de Pacientes");
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20,0,10,0));

        btnRegistarPaciente = new JButton("Registrar Paciente");
        btnModificarPaciente = new JButton("Modificar Paciente");
        btnVolver = new JButton("Volver");
    }

    private void aplicarEstilos() {
        Estilos.aplicarEstiloTitulo(lblTitulo);
        Estilos.aplicarEstiloBoton(btnModificarPaciente, btnRegistarPaciente, btnVolver);
    }

    private void configurarLayout() {
        aplicarEstilos();
        setLayout(new BorderLayout());

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

        btnRegistarPaciente.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnModificarPaciente.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelBotones.add(Box.createVerticalStrut(100));
        panelBotones.add(btnRegistarPaciente);
        panelBotones.add(Box.createVerticalStrut(10));
        panelBotones.add(btnModificarPaciente);
        panelBotones.add(Box.createVerticalStrut(10));
        panelBotones.add(btnVolver);

        JPanel centro = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.weighty = 0.3;
        gbc.anchor = GridBagConstraints.NORTH;
        centro.add(panelBotones, gbc);

        add(centro, BorderLayout.CENTER);
    }

    // getters y setters
    public JButton getBtnRegistrarPaciente() { return btnRegistarPaciente; }
    public JButton getBtnModificarPaciente() { return btnModificarPaciente; }
    public JButton getBtnVolver() { return btnVolver; }

}

