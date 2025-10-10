package ConsultorioMedico.vista;

import ConsultorioMedico.util.Estilos;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class MenuPanel extends JPanel {
    private JButton btnRegistrarPaciente;
    private JButton btnAsignarTurno;
    private JButton btnBuscarPaciente;
    private JLabel lblTitulo;

    public MenuPanel() {
        inicializarComponentes();
        configurarLayout();
    }

    private void inicializarComponentes() {
        lblTitulo = new JLabel("Consultorio Médico Roberto");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        btnRegistrarPaciente = new JButton("Registrar Paciente");
        btnBuscarPaciente = new JButton("Buscar Paciente");
        btnAsignarTurno = new JButton("Asignar Turno");
    }

    private void configurarLayout() {
        this.setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelBotones.add(btnRegistrarPaciente);
        panelBotones.add(btnBuscarPaciente);
        panelBotones.add(btnAsignarTurno);

        add(lblTitulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        Estilos.aplicarEstiloPanel(panelBotones);
        Estilos.aplicarEstiloBoton(btnRegistrarPaciente, btnBuscarPaciente, btnAsignarTurno);
        Estilos.aplicarEstiloTitulo(lblTitulo);
    }

    // getters
    public JButton getBtnRegistrarPaciente() { return btnRegistrarPaciente; }
    public JButton getBtnAsignarTurno() { return btnAsignarTurno; }
    public JButton getBtnBuscarPaciente() { return btnBuscarPaciente; }

}
