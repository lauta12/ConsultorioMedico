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
    private PacienteDAO pacienteDAO;
    private BuscarPacientePanel panelBuscar;
    private Validacion validador;

    public BuscarPacienteControlador(ConsultorioMedicoVista vista) {
        this.vista = vista;
        this.panelBuscar = vista.getBuscarPacientePanel();
        this.pacienteDAO = new PacienteDAO();
        this.validador = new Validacion(vista);

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
            int[] filasSeleccionadas = vista.getBuscarPacientePanel().getTablaPacientes().getSelectedRows();

            if(filasSeleccionadas.length == 0) {
                JOptionPane.showMessageDialog(null, "Selecciona al menos un paciente para eliminar.");
                return;
            }

            //TODO: permitir borrar todos los campos seleccionados, actualmente te borra uno solo aunque selecciones muchos.
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Seguro que querés eliminar " + filasSeleccionadas.length + " paciente(s)?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if(confirm == JOptionPane.YES_OPTION) {
                DefaultTableModel modelo = vista.getBuscarPacientePanel().getModelo();

                for(int i = filasSeleccionadas.length - 1; i >=0; i--) {
                    String dni = (String) modelo.getValueAt(filasSeleccionadas[i], 0);
                    pacienteDAO.eliminarPorDni(dni);
                }

                JOptionPane.showMessageDialog(null, "Paciente(s) eliminado(s) correctamente.");
                validador.cargarPacientesEnTabla();
            }
        });

    }
}
