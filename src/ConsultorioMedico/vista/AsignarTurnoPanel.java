package ConsultorioMedico.vista;

import com.toedter.calendar.JDateChooser;
import ConsultorioMedico.util.Estilos;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class AsignarTurnoPanel extends JPanel {
    // componentes
    private JComboBox<String> comboPacientes;
    private JComboBox<String> comboDoctores;
    private JDateChooser dateChooser;
    private JTextField txtHora;
    private JButton btnAsignar;
    private JButton btnCancelar;

    private JLabel lblTitulo;
    private JLabel lblPaciente;
    private JLabel lblDoctor;
    private JLabel lblFecha;
    private JLabel lblHora;

    public AsignarTurnoPanel() {
        inicializarComponentes();
        configurarLayout();
    }

    private void inicializarComponentes() {
        lblTitulo = new JLabel("Asignar Turno");
        lblPaciente = new JLabel("Paciente:");
        comboPacientes = new JComboBox<>();
        lblDoctor = new JLabel("Doctor:");
        comboDoctores = new JComboBox<>();
        lblFecha = new JLabel("Fecha:");
        dateChooser = new JDateChooser();
        lblHora = new JLabel("Hora (HH:MM):");
        txtHora = new JTextField();
        btnAsignar = new JButton("Asignar Turno");
        btnCancelar = new JButton("Cancelar");
    }

    private void aplicarEstilos() {
        Estilos.aplicarEstiloTitulo(lblTitulo);
        Estilos.aplicarEstiloLabel(lblFecha, lblDoctor, lblHora, lblPaciente);
        Estilos.aplicarEstiloTextField(txtHora);
        Estilos.aplicarEstiloBoton(btnAsignar, btnCancelar);
        Estilos.aplicarEstiloComboBox(comboDoctores, comboPacientes);
        Estilos.aplicarEstiloDateChooser(dateChooser);
    }

    private void configurarLayout() {
        aplicarEstilos();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = 1;
        add(lblPaciente, gbc);

        gbc.gridx = 1;
        add(comboPacientes, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(lblDoctor, gbc);

        gbc.gridx = 1;
        add(comboDoctores, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(lblFecha, gbc);

        gbc.gridx = 1;
        add(dateChooser, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(lblHora, gbc);

        gbc.gridx = 1;
        add(txtHora, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        add(btnAsignar, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        add(btnCancelar, gbc);
    }

    public JComboBox<String> getComboDoctores() { return comboDoctores; }
    public JComboBox<String> getComboPacientes() { return comboPacientes; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
