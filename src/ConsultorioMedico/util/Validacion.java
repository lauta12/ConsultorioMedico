package ConsultorioMedico.util;

import ConsultorioMedico.dao.PacienteDAO;
import ConsultorioMedico.modelo.Doctor;
import ConsultorioMedico.modelo.Paciente;
import ConsultorioMedico.vista.paciente.RegistrarPacientePanel;
import ConsultorioMedico.vista.ConsultorioMedicoVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Validacion {
    private ConsultorioMedicoVista vista;
    private PacienteDAO dao;

    public Validacion(ConsultorioMedicoVista vista) {
        this.vista = vista;
        this.dao = new PacienteDAO();
    }

    public boolean validarDatos(RegistrarPacientePanel panelRegistrar) {
        boolean valido = true;

        String nombre = panelRegistrar.getTxtNombre().getText();
        String apellido = panelRegistrar.getTxtApellido().getText();
        String dni = panelRegistrar.getTxtDni().getText();
        String telefono = panelRegistrar.getTxtTelefono().getText();
        String obraSocial = panelRegistrar.getObraSocialSeleccionada();

        if (nombre.trim().isEmpty() || !nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) {
            valido = false;
        } else {
            panelRegistrar.setNombre(capitalizarNombre(nombre));
        }

        if (apellido.trim().isEmpty() || !apellido.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) {
            valido = false;
        } else {
            panelRegistrar.setApellido(capitalizarNombre(apellido));
        }

        if(dni.trim().isEmpty() || !dni.trim().matches("\\d{8}")) {
            valido = false;
        }

        if(telefono.trim().isEmpty() || !telefono.matches("\\d+") || telefono.length() < 8) {
            valido = false;
        }

        if(obraSocial == null || obraSocial.trim().isEmpty() || obraSocial.equals("Seleccionar obra social")) {
            valido = false;
        }

        return valido;
    }

    public boolean validarDatosBasicos(String nombre, String apellido, String telefono, Component parent) {
        if (nombre.trim().isEmpty() || !nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) {
            JOptionPane.showMessageDialog(parent, "El nombre no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (apellido.trim().isEmpty() || !apellido.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) {
            JOptionPane.showMessageDialog(parent, "El apellido no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (telefono.trim().isEmpty() || !telefono.matches("\\d+") || telefono.length() < 8) {
            JOptionPane.showMessageDialog(parent, "El teléfono no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }



    public String capitalizarNombre(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return "";
        }

        // Pasamos to/do a minúsculas primero
        texto = texto.toLowerCase().trim();

        // Dividimos por espacios (por si hay más de un nombre/apellido)
        String[] palabras = texto.split("\\s+");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            // Primera letra en mayúscula + resto en minúscula
            resultado.append(Character.toUpperCase(palabra.charAt(0)))
                    .append(palabra.substring(1))
                    .append(" ");
        }

        return resultado.toString().trim();
    }

    public void cargarPacientesEnTabla() {
        DefaultTableModel modelo = vista.getModificarPacientePanel().getModelo();
        modelo.setRowCount(0);

        for (Paciente p : dao.listarTodos()) {
            Object[] fila = {
                    p.getDni(),
                    p.getNombre(),
                    p.getApellido(),
                    p.getTelefono(),
                    p.getObraSocial()
            };
            modelo.addRow(fila);
        }
    }


    public static boolean validarTurno(Component parent, Paciente pacienteSeleccionado,
                                       Doctor doctorSeleccionado, LocalDate fecha, LocalTime hora) {
        LocalDate hoy = LocalDate.now();
        LocalDate maxFecha = hoy.plusMonths(3);
        LocalTime ahora = LocalTime.now();
        if (pacienteSeleccionado == null) {
            JOptionPane.showMessageDialog(parent,
                    "Debe seleccionar un paciente.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (doctorSeleccionado == null) {
            JOptionPane.showMessageDialog(parent,
                    "Debe seleccionar un doctor.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (fecha == null || hora == null) {
            JOptionPane.showMessageDialog(parent,
                    "Debe seleccionar fecha y hora.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (fecha.isBefore(hoy)) {
            JOptionPane.showMessageDialog(parent,
                    "No puede asignar un turno en una fecha anterior a hoy.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // no permite horas anteriores a la actual
        if (fecha.equals(hoy) && hora.isBefore(ahora)) {
            JOptionPane.showMessageDialog(parent,
                    "No puede asignar un turno antes de la hora actual.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // no permite asignar un turno después de 3 meses
        if(fecha.isAfter(maxFecha)) {
            JOptionPane.showMessageDialog(parent,
                    "No puede asignar un turno después de 3 meses",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}