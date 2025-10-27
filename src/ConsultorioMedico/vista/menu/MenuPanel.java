package ConsultorioMedico.vista.menu;

import ConsultorioMedico.util.Estilos;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class MenuPanel extends JPanel {
    private JButton btnGestionarPaciente;
    private JButton btnGestionarTurno;

    private JLabel lblTitulo;
    private JLabel lblVersion;

    public MenuPanel() {
        inicializarComponentes();
        configurarLayout();
    }

    private void inicializarComponentes() {
        lblTitulo = new JLabel("Consultorio Médico Roberto");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        btnGestionarPaciente = new JButton("Gestionar Paciente");
        btnGestionarTurno = new JButton("Gestionar Turno");

        lblVersion = new JLabel("Versión 3.0.1");
    }

    private void configurarLayout() {
        this.setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelBotones.add(btnGestionarPaciente);
        panelBotones.add(btnGestionarTurno);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(lblVersion, BorderLayout.SOUTH);

        Estilos.aplicarEstiloTitulo(lblTitulo);
        Estilos.aplicarEstiloBoton(btnGestionarPaciente, btnGestionarTurno);
    }

    // getters
    public JButton getBtnGestionarPaciente() { return btnGestionarPaciente; }
    public JButton getBtnGestionarTurno() { return btnGestionarTurno; }

}
