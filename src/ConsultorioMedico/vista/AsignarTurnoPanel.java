package ConsultorioMedico.vista;

import ConsultorioMedico.modelo.Doctor;
import ConsultorioMedico.modelo.Paciente;
import ConsultorioMedico.util.Estilos;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Date;

public class AsignarTurnoPanel extends JPanel {
    // componentes
    private JComboBox<Paciente> comboPacientes;
    private JComboBox<Doctor> comboDoctores;
    private JDateChooser dateChooser;
    private JTextField txtHora;
    private JButton btnAsignar;
    private JButton btnCancelar;

    private JLabel lblTitulo;
    private JLabel lblPaciente;
    private JLabel lblDoctor;
    private JLabel lblFecha;
    private JLabel lblHora;

    String[] opciones = {
            "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00",
            "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
            "15:30", "16:00", "16:30", "17:00", "17:30"
    };

    private JComboBox<String> comboHora;

    public AsignarTurnoPanel() {
        inicializarComponentes();
        configurarLayout();
    }

    private void inicializarComponentes() {
        lblTitulo = new JLabel("Asignar Turno");
        lblPaciente = new JLabel("Paciente:");
        lblDoctor = new JLabel("Doctor:");
        lblFecha = new JLabel("Fecha:");
        lblHora = new JLabel("Hora:");

        comboPacientes = new JComboBox<>();
        comboDoctores = new JComboBox<>();
        comboHora = new JComboBox<>(opciones);
        dateChooser = new JDateChooser();

        btnAsignar = new JButton("Guardar Turno");
        btnCancelar = new JButton("Cancelar");
    }

    private void aplicarEstilos() {
        Estilos.aplicarEstiloCalendario(dateChooser);
        Estilos.aplicarEstiloTitulo(lblTitulo);
    }

    private void configurarLayout() {
        aplicarEstilos();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);
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
        add(comboHora, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        add(btnAsignar, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        add(btnCancelar, gbc);
    }

    public JComboBox<Doctor> getComboDoctores() { return comboDoctores; }
    public JComboBox<Paciente> getComboPacientes() { return comboPacientes; }
    public JComboBox<String> getComboHora() { return comboHora; }
    public JButton getBtnCancelar() { return btnCancelar; }
    public JButton getBtnAsignar() { return btnAsignar; }
    public JTextField getTxtHora() { return txtHora; }
    public String getHoraSeleccionada() { return (String) comboHora.getSelectedItem(); }
    public Date getFechaSeleccionada() { return dateChooser.getDate(); }
    public JDateChooser getDateChooser() { return dateChooser; }
}
