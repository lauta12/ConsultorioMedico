package ConsultorioMedico.controlador.turno;

import ConsultorioMedico.modelo.Doctor;
import ConsultorioMedico.modelo.Paciente;
import ConsultorioMedico.modelo.Turno;
import ConsultorioMedico.dao.PacienteDAO;
import ConsultorioMedico.dao.TurnoDAO;
import ConsultorioMedico.dao.DoctorDAO;
import ConsultorioMedico.util.Estilos;
import ConsultorioMedico.vista.ConsultorioMedicoVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ModificarTurnoControlador {

    private ConsultorioMedicoVista vista;
    private TurnoDAO turnoDAO;
    private DoctorDAO doctorDAO;
    private PacienteDAO pacienteDAO;

    public ModificarTurnoControlador(ConsultorioMedicoVista vista) {
        this.vista = vista;
        this.turnoDAO = new TurnoDAO();
        this.doctorDAO = new DoctorDAO();
        this.pacienteDAO = new PacienteDAO();

        cargarTablaTurnos();

        vista.getModificarTurnoPanel().getBtnBuscar().addActionListener(e -> {
            String fechaStr = vista.getModificarTurnoPanel().getTxtBusqueda().getText().trim();  // Fecha del campo txtBusqueda
            String estado = (String) vista.getModificarTurnoPanel().getComboEstado().getSelectedItem();  // Estado del combo
            LocalDate fecha = null;
            if (!fechaStr.isEmpty()) {
                try {
                    fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));  // Ajusta el formato si es YYYY-MM-DD
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use dd/MM/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            // Llama a cargarTablaTurnos con filtros (null para dni, nombrePaciente, nombreDoctor por ahora)
            cargarTablaTurnos(null, fecha, estado.isEmpty() ? null : estado, null, null);
        });


        vista.getModificarTurnoPanel().getBtnLimpiar().addActionListener(e -> {
            Estilos.limpiarTextFields(vista.getModificarTurnoPanel());
            vista.getModificarTurnoPanel().getComboEstado().setSelectedIndex(0);
            cargarTablaTurnos();
        });

        vista.getModificarTurnoPanel().getBtnVolver().addActionListener(e -> {
            vista.mostrarPantalla("menuGestionarTurno");
            Estilos.limpiarTextFields(vista.getModificarTurnoPanel().getTablaTurnos());
        });

        vista.getModificarTurnoPanel().getBtnEliminar().addActionListener(e -> {
            int filaSeleccionada = vista.getModificarTurnoPanel().getTablaTurnos().getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione un turno para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idTurno = (int) vista.getModificarTurnoPanel().getTablaTurnos().getValueAt(filaSeleccionada, 0);

            int confirmacion = JOptionPane.showConfirmDialog(null,
                    "¿Está seguro de eliminar este turno?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean eliminado = turnoDAO.eliminarTurno(idTurno);

                if (eliminado) {
                    JOptionPane.showMessageDialog(null, "Turno eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarTablaTurnos();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el turno.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        vista.getModificarTurnoPanel().getBtnLimpiar().addActionListener(e -> {
            Estilos.limpiarTextFields(vista.getModificarTurnoPanel());
        });

        vista.getModificarTurnoPanel().getBtnMarcarCompletado().addActionListener(e -> {
            actualizarEstadoTurno("Completado");
        });

        vista.getModificarTurnoPanel().getBtnMarcarCancelado().addActionListener(e -> {
            actualizarEstadoTurno("Cancelado");
        });

    }

    private void actualizarEstadoTurno(String nuevoEstado) {
        int columnaID = 0;
        int columnaEstado = 5;

        int filaSeleccionada = vista.getModificarTurnoPanel().getTablaTurnos().getSelectedRow();

        if(filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                    null,
                    "Seleccione un turno para actualizar.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        int idTurno = (int) vista.getModificarTurnoPanel().getTablaTurnos().getValueAt(filaSeleccionada, columnaID);
        String estadoActual = (String) vista.getModificarTurnoPanel().getTablaTurnos().getValueAt(filaSeleccionada, columnaEstado);

        // verifica si el estado es el mismo
        if(estadoActual.equals(nuevoEstado)) {
            JOptionPane.showMessageDialog(
                    null,
                    "El turno ya está marcado como " + nuevoEstado + ".",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "Marcar el turno como " + nuevoEstado + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if(confirmacion == JOptionPane.YES_OPTION) {
            boolean actualizado = turnoDAO.actualizarEstado(idTurno, nuevoEstado);

            if(actualizado) {
                JOptionPane.showMessageDialog(
                        null,
                        "Turno marcado como " + nuevoEstado + " correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                );
                cargarTablaTurnos();
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "No se pudo actualizar el turno.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void cargarTablaTurnos(String dni, LocalDate fecha, String estado, String nombrePaciente, String nombreDoctor) {
        DefaultTableModel modeloTabla = (DefaultTableModel) vista.getModificarTurnoPanel().getTablaTurnos().getModel();
        modeloTabla.setRowCount(0); // limpia la tabla

        List<Turno> listaTurnos = turnoDAO.buscarTurnos(dni, fecha, estado, nombrePaciente, nombreDoctor);

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        for (Turno turno : listaTurnos) {
//            Paciente paciente = pacienteDAO.buscarPorDni(turno.getDniPaciente());
//            Doctor doctor = doctorDAO.buscarPorId(turno.getIdDoctor());
            String nombrePacienteTurno = (turno.getNombrePaciente() != null) ? turno.getNombrePaciente() : "Desconocido";
            String nombreDoctorTurno = (turno.getNombreDoctor() != null) ? turno.getNombreDoctor() : "Desconocido";

            turno.setNombrePaciente(nombrePaciente);
            turno.setNombreDoctor(nombreDoctor);

            modeloTabla.addRow(new Object[]{
                    turno.getIdTurno(),
                    nombrePacienteTurno,
                    nombreDoctorTurno,
                    turno.getFecha().format(formatoFecha),
                    turno.getHora().format(formatoHora),
                    turno.getEstado()
            });

            // si no hay turnos, muestra un mensaje
            if (modeloTabla.getRowCount() == 0) {
                JOptionPane.showMessageDialog(
                        vista.getModificarTurnoPanel(),
                        "No se encontraron turnos.",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    private void cargarTablaTurnos() {
        cargarTablaTurnos(null, null, null, null, null);
    }
}
