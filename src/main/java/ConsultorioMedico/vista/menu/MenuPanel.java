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

    public MenuPanel() {
        inicializarComponentes();
        configurarLayout();
    }

    private void inicializarComponentes() {
        lblTitulo = new JLabel("Consultorio Médico Roberto");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        btnGestionarPaciente = new JButton("Gestionar Paciente");
        btnGestionarTurno = new JButton("Gestionar Turno");
    }

    private void configurarLayout() {
        this.setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelBotones.add(btnGestionarPaciente);
        panelBotones.add(btnGestionarTurno);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        Estilos.aplicarEstiloTitulo(lblTitulo);
        Estilos.aplicarEstiloBoton(btnGestionarPaciente, btnGestionarTurno);
    }

    // getters
    public JButton getBtnGestionarPaciente() { return btnGestionarPaciente; }
    public JButton getBtnGestionarTurno() { return btnGestionarTurno; }
}
