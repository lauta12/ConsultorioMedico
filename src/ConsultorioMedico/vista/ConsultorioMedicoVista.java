package ConsultorioMedico.vista;

import ConsultorioMedico.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.BorderLayout;

public class ConsultorioMedicoVista extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelPrincipal;

    // paneles
    private MenuPanel menuPanel;
    private RegistrarPacientePanel registrarPacientePanel;
    private BuscarPacientePanel buscarPacientePanel;
    private AsignarTurnoPanel asignarTurnoPanel;

    // guardo el boton
    private JButton btnVolver;

    public ConsultorioMedicoVista() {
        super("Consultorio Medico");
        inicializarComponentes();
        configurarLayout();
        configurarVentana();
    }

    private void inicializarComponentes() {
        menuPanel = new MenuPanel();
        registrarPacientePanel = new RegistrarPacientePanel();
        buscarPacientePanel = new BuscarPacientePanel();
        asignarTurnoPanel = new AsignarTurnoPanel();
    }

    private void configurarVentana() {
        this.setSize(1366, 768);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void configurarLayout() {
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        panelPrincipal.add(menuPanel, "menu");
        panelPrincipal.add(registrarPacientePanel, "registrar");
        panelPrincipal.add(buscarPacientePanel, "buscar");
        panelPrincipal.add(asignarTurnoPanel, "turno");

        this.add(panelPrincipal, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public MenuPanel getMenuPanel() { return menuPanel; }
    public RegistrarPacientePanel getRegistrarPacientePanel() { return registrarPacientePanel; }
    public BuscarPacientePanel getBuscarPacientePanel() { return buscarPacientePanel; }
    public AsignarTurnoPanel getAsignarTurnoPanel() { return asignarTurnoPanel; }

    public void mostrarPantalla(String nombrePantalla) {
        cardLayout.show(panelPrincipal, nombrePantalla);
    }

}
