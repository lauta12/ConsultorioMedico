package ConsultorioMedico.controlador;

import ConsultorioMedico.dao.PacienteDAO;
import ConsultorioMedico.modelo.Paciente;
import ConsultorioMedico.util.Estilos;
import ConsultorioMedico.util.Validacion;
import ConsultorioMedico.vista.BuscarPacientePanel;
import ConsultorioMedico.vista.ConsultorioMedicoVista;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BuscarPacienteControlador {
    private ConsultorioMedicoVista vista;
    private PacienteDAO pacienteDAO = new PacienteDAO();
    private BuscarPacientePanel panelBuscar;
    private Validacion validador = new Validacion(vista);

    public BuscarPacienteControlador(ConsultorioMedicoVista vista) {
        this.vista = vista;
        this.panelBuscar = vista.getBuscarPacientePanel();

        // boton Buscar
        vista.getBuscarPacientePanel().getBtnBuscar().addActionListener(e -> {
            String dni = vista.getBuscarPacientePanel().getDni().trim();

            if(dni.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Ingrese un DNI para buscar.",
                        "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Paciente paciente = pacienteDAO.buscarPorDni(vista.getBuscarPacientePanel().getDni());
            DefaultTableModel modelo = vista.getBuscarPacientePanel().getModelo();

            modelo.setRowCount(0);

            if(paciente != null) {
                Object[] fila = {
                        paciente.getDni(),
                        paciente.getNombre(),
                        paciente.getApellido(),
                        paciente.getTelefono(),
                        paciente.getObraSocial()
                };
                modelo.addRow(fila);
            } else {
                JOptionPane.showMessageDialog(vista, "No se encontró paciente con ese DNI",
                        "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }

        });


        // Panel BuscarPaciente. Botón cancelar
        vista.getBuscarPacientePanel().getBtnCancelar().addActionListener(e -> {
            vista.mostrarPantalla("menu");
            Estilos.limpiarTextFields(vista.getBuscarPacientePanel());
        });

        // Panel BuscarPaciente. Boton Actualizar
        vista.getBuscarPacientePanel().getBtnActualizar().addActionListener(e -> {
            DefaultTableModel modelo = vista.getBuscarPacientePanel().getModelo();
            modelo.setRowCount(0);
            validador.cargarPacientesEnTabla();
            Estilos.limpiarTextFields(panelBuscar);
        });

        // Panel Buscar Paciente. Boton Eliminar
        vista.getBuscarPacientePanel().getBtnEliminar().addActionListener(e -> {
            int filaSeleccionada = vista.getBuscarPacientePanel().getTablaPacientes().getSelectedRow();

            if(filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona un paciente para eliminar.");
                return;
            }

            String dni = (String) vista.getBuscarPacientePanel().getModelo().getValueAt(filaSeleccionada, 0);

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Seguro que queres eliminar al paciente con DNI: " + dni + "?",
                    "Confirmar eliminacion", JOptionPane.YES_NO_OPTION);


            if(confirm == JOptionPane.YES_OPTION) {
                pacienteDAO.eliminarPorDni(dni);
                JOptionPane.showMessageDialog(null, "Paciente eliminado correctamente.");

            }
            validador.cargarPacientesEnTabla();
        });


    }
}
