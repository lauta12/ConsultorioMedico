package ConsultorioMedico.vista.paciente;

import ConsultorioMedico.util.Estilos;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class RegistrarPacientePanel extends JPanel {
    private JLabel lblTitulo, lblNombre, lblApellido, lblDni, lblObraSocial, lblTelefono;
    private JTextField txtNombre, txtApellido, txtDni, txtTelefono;
    private JButton btnGuardar, btnCancelar;

    String[] opciones = {"Seleccionar obra social", "No Tiene", "OSDE", "Swiss Medical", "Galeno", "Medicus", "Omint",
            "Sancor Salud", "Federada Salud", "Prevencion Salud" };
    private JComboBox<String> comboObraSocial = new JComboBox<>(opciones);

    public RegistrarPacientePanel() {
        inicializarComponentes();
        configurarLayout();
    }

    private void inicializarComponentes() {
        lblTitulo = new JLabel("Registrar paciente", SwingConstants.CENTER);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(12);

        lblApellido = new JLabel("Apellido:");
        txtApellido = new JTextField(12);

        lblDni = new JLabel("DNI:");
        txtDni = new JTextField(12);

        lblObraSocial = new JLabel("Obra Social:");
        comboObraSocial = new JComboBox<>(opciones);
        comboObraSocial.setSelectedIndex(0);

        lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField(12);

        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
    }

    private void aplicarEstilos() {
        Estilos.aplicarEstiloTitulo(lblTitulo);
    }

    private void configurarLayout() {
        aplicarEstilos();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitulo, gbc);

        // se resetea
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 1; add(lblNombre, gbc);
        gbc.gridx = 1; add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(lblApellido, gbc);
        gbc.gridx = 1; add(txtApellido, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(lblDni, gbc);
        gbc.gridx = 1; add(txtDni, gbc);

        gbc.gridx = 0; gbc.gridy = 4; add(lblObraSocial, gbc);
        gbc.gridx = 1; add(comboObraSocial, gbc);

        gbc.gridx = 0; gbc.gridy = 5; add(lblTelefono, gbc);
        gbc.gridx = 1; add(txtTelefono, gbc);

        gbc.gridx = 0; gbc.gridy = 6; add(btnGuardar, gbc);
        gbc.gridx = 1; add(btnCancelar, gbc);
    }

    // getters
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnCancelar() { return btnCancelar; }
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtApellido() { return txtApellido; }
    public JTextField getTxtDni() { return txtDni; }
    public JTextField getTxtTelefono() { return txtTelefono; }
    public JComboBox<String> getComboObraSocial() { return comboObraSocial; }
    public String getObraSocialSeleccionada() { return (String) comboObraSocial.getSelectedItem(); }

    public String getNombre() { return txtNombre.getText(); }
    public String getApellido() { return txtApellido.getText(); }
    public String getDni() { return txtDni.getText(); }
    public String getTelefono() { return txtTelefono.getText(); }

    // setters
    public void setNombre(String nombre) { txtNombre.setText(nombre); }
    public void setApellido(String apellido) { txtApellido.setText(apellido); }

}
