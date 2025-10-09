package ConsultorioMedico.vista;

import ConsultorioMedico.util.*;
import javax.swing.*;
import java.awt.*;

public class ConsultorioMedicoVista extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelPrincipal;

    // paneles
    private MenuPanel menuPanel;
    private RegistrarPacientePanel registrarPacientePanel;
    private BuscarPacientePanel buscarPacientePanel;
    private SacarTurnoPanel sacarTurnoPanel;

    // guardo el boton
    private JButton btnVolver;

    public ConsultorioMedicoVista() {
        super("Consultorio Medico");
        inicializarComponentes();
        configurarLayout();
        aplicarEstilos();
        configurarVentana();
    }

    private void inicializarComponentes() {
        menuPanel = new MenuPanel();
        registrarPacientePanel = new RegistrarPacientePanel();
        buscarPacientePanel = new BuscarPacientePanel();
        sacarTurnoPanel = new SacarTurnoPanel();
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
        panelPrincipal.add(sacarTurnoPanel, "turno");

        this.add(panelPrincipal, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public MenuPanel getMenuPanel() { return menuPanel; }
    public RegistrarPacientePanel getRegistrarPacientePanel() { return registrarPacientePanel; }
    public BuscarPacientePanel getBuscarPacientePanel() { return buscarPacientePanel; }

    public void mostrarPantalla(String nombrePantalla) {
        cardLayout.show(panelPrincipal, nombrePantalla);
    }

    private void aplicarEstilos() {
        Estilos.aplicarEstiloPanel(menuPanel, registrarPacientePanel, buscarPacientePanel, sacarTurnoPanel);
    }
}
