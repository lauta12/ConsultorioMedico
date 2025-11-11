package ConsultorioMedico.vista.menu;

import ConsultorioMedico.util.Estilos;

import javax.swing.*;
import java.awt.*;

public class MenuGestionarTurnoPanel extends JPanel {

    private JButton btnAsignarTurno;
    private JButton btnVolver;
    private JButton btnModificarTurno;
    private JLabel lblTitulo;

    public MenuGestionarTurnoPanel() {
        inicializarComponentes();
        configurarLayout();
    }

    private void inicializarComponentes() {
        lblTitulo = new JLabel("Gestión de Turnos");
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20,0,10,0));

        btnAsignarTurno = new JButton("Asignar Turno");
        btnModificarTurno = new JButton("Modificar Turno");
        btnVolver = new JButton("Volver");
    }

    private void aplicarEstilos() {
        Estilos.aplicarEstiloTitulo(lblTitulo);
        Estilos.aplicarEstiloBoton(btnAsignarTurno, btnModificarTurno, btnVolver);
    }

    private void configurarLayout() {
        aplicarEstilos();
        setLayout(new BorderLayout());

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));

        btnAsignarTurno.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnModificarTurno.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelBotones.add(Box.createVerticalStrut(10));
        panelBotones.add(btnAsignarTurno);
        panelBotones.add(Box.createVerticalStrut(10));
        panelBotones.add(btnModificarTurno);
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
    public JButton getBtnAsignarTurno() { return btnAsignarTurno; }
    public JButton getBtnModificarTurno() { return btnModificarTurno; }
    public JButton getBtnVolver() { return btnVolver; }
}


