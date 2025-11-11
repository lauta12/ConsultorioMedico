package ConsultorioMedico.vista;

import ConsultorioMedico.controlador.turno.ModificarTurnoControlador;
import ConsultorioMedico.vista.menu.MenuGestionarPacientePanel;
import ConsultorioMedico.vista.menu.MenuGestionarTurnoPanel;
import ConsultorioMedico.vista.menu.MenuPanel;
import ConsultorioMedico.vista.paciente.ModificarPacientePanel;
import ConsultorioMedico.vista.paciente.RegistrarPacientePanel;
import ConsultorioMedico.vista.turno.AsignarTurnoPanel;
import ConsultorioMedico.vista.turno.ModificarTurnoPanel;

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
    private ModificarPacientePanel modificarPacientePanel;
    private AsignarTurnoPanel asignarTurnoPanel;
    private MenuGestionarTurnoPanel menuGestionarTurnoPanel;
    private MenuGestionarPacientePanel menuGestionarPacientePanel;
    private ModificarTurnoPanel modificarTurnoPanel;
    private ModificarTurnoControlador modificarTurnoControlador;


    public ConsultorioMedicoVista() {
        super("Consultorio Medico");
        inicializarComponentes();
        configurarLayout();
        configurarVentana();
    }

    private void inicializarComponentes() {
        menuPanel = new MenuPanel();
        registrarPacientePanel = new RegistrarPacientePanel();
        menuGestionarPacientePanel = new MenuGestionarPacientePanel();
        menuGestionarTurnoPanel = new MenuGestionarTurnoPanel();
        modificarPacientePanel = new ModificarPacientePanel();
        asignarTurnoPanel = new AsignarTurnoPanel();
        modificarTurnoPanel = new ModificarTurnoPanel();
    }

    private void configurarVentana() {
        this.setSize(1366, 768);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void configurarLayout() {
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        panelPrincipal.add(menuPanel, "menuPanel");
        panelPrincipal.add(registrarPacientePanel, "registrarPaciente");
        panelPrincipal.add(menuGestionarPacientePanel, "menuGestionarPaciente");
        panelPrincipal.add(menuGestionarTurnoPanel, "menuGestionarTurno");
        panelPrincipal.add(asignarTurnoPanel, "asignarTurno");
        panelPrincipal.add(modificarPacientePanel, "modificarPaciente");
        panelPrincipal.add(modificarTurnoPanel, "modificarTurno");


        this.add(panelPrincipal, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public MenuPanel getMenuPanel() { return menuPanel; }
    public RegistrarPacientePanel getRegistrarPacientePanel() { return registrarPacientePanel; }
    public ModificarPacientePanel getModificarPacientePanel() { return modificarPacientePanel; }
    public AsignarTurnoPanel getAsignarTurnoPanel() { return asignarTurnoPanel; }
    public MenuGestionarPacientePanel getMenuGestionarPacientePanel() { return menuGestionarPacientePanel; }
    public MenuGestionarTurnoPanel getMenuGestionarTurnoPanel() { return menuGestionarTurnoPanel; }
    public ModificarTurnoPanel getModificarTurnoPanel() { return modificarTurnoPanel; }

    // getter para el controlador
    public ModificarTurnoControlador getModificarTurnoControlador() { return modificarTurnoControlador; }

    public void mostrarPantalla(String nombrePantalla) {
        // crea el controlador solo si no existe y es la pantalla correspondiente
        if ("modificarTurno".equals(nombrePantalla) && modificarTurnoControlador == null) {
            modificarTurnoControlador = new ModificarTurnoControlador(this);
        }

        cardLayout.show(panelPrincipal, nombrePantalla);
    }

}
