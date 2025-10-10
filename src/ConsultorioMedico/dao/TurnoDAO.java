package ConsultorioMedico.dao;

import ConsultorioMedico.modelo.Turno;
import ConsultorioMedico.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TurnoDAO {
    public void guardarTurno(Turno turno) {

        String sql = "INSERT INTO turnos (dni_paciente, id_doctor, fecha, hora) VALUES (?,?,?,?,?)";

        try(Connection conn = Conexion.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNombre());
            stmt.setString(2, paciente.getApellido());
            stmt.setString(3, paciente.getDni());
            stmt.setString(4, paciente.getObraSocial());
            stmt.setString(5, paciente.getTelefono());

            stmt.executeUpdate();

            System.out.println("Paciente guardado en la base de datos");

        } catch(SQLException e) {
            e.printStackTrace();
        }


    }

    public void listarTurnos() {

    }

    public void listarPorDni(String dniPaciente) {

    }

    public void eliminarTurno(int id) {

    }

    public void actualizarEstado(int id, String nuevoEstado) {

    }
}
