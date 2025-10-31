package ConsultorioMedico.dao;

import ConsultorioMedico.modelo.Doctor;
import ConsultorioMedico.modelo.Turno;
import ConsultorioMedico.util.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Statement;
import java.sql.Time;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TurnoDAO {
    // guardar
    public boolean guardarTurno(Turno turno) {
        String sql = "INSERT INTO turnos (dni_paciente, id_doctor, fecha, hora, estado) VALUES (?,?,?,?, 'Pendiente')";

        try(Connection conn = Conexion.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, turno.getDniPaciente());
            stmt.setInt(2, turno.getIdDoctor());
            stmt.setDate(3, Date.valueOf(turno.getFecha()));
            stmt.setTime(4, Time.valueOf(turno.getHora()));
            //stmt.setString(5, turno.getEstado());

            stmt.executeUpdate();
            return true;

        } catch(SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    public boolean existeTurno(Doctor doctor, LocalDate fecha, LocalTime hora) {
        String sql = "SELECT COUNT(*) FROM turnos WHERE id_doctor = ? AND fecha = ? AND hora = ?";

        try(Connection conn = Conexion.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, doctor.getId_doctor());
            ps.setDate(2, Date.valueOf(fecha));
            ps.setTime(3, Time.valueOf(hora));

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Turno> listarTodos() {
        List<Turno> lista = new ArrayList<>();

        String sql = "SELECT t.id_turno, t.fecha, t.hora, t.estado, p.nombre AS nombre_paciente, d.nombre AS nombre_doctor " +
                "FROM turnos t " +
                "JOIN pacientes p ON t.dni_paciente = p.dni " +
                "JOIN doctores d ON t.id_doctor = d.id_doctor";

        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Turno turno = new Turno();
                turno.setIdTurno(rs.getInt("id_turno"));
                turno.setNombrePaciente(rs.getString("nombre_paciente"));
                turno.setNombreDoctor(rs.getString("nombre_doctor"));
                turno.setFecha(rs.getDate("fecha").toLocalDate());
                turno.setHora(rs.getTime("hora").toLocalTime());
                turno.setEstado(rs.getString("estado"));
                lista.add(turno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean eliminarTurno(int idTurno) {
        String sql = "DELETE FROM turnos WHERE id_turno = ?";

        try(Connection conn = Conexion.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idTurno);

            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();

            return false;
        }
    }

    public List<Turno> buscarTurnos(String dniPaciente, LocalDate fecha, String estado, String nombrePaciente, String nombreDoctor) {
        List<Turno> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT t.id_turno, t.fecha, t.hora, t.estado, " +
                        "CONCAT(p.nombre, ' ', p.apellido) AS nombre_paciente, " +
                        "d.nombre AS nombre_doctor " +
                        "FROM turnos t " +
                        "JOIN pacientes p ON t.dni_paciente = p.dni " +
                        "JOIN doctores d ON t.id_doctor = d.id_doctor " +
                        "WHERE 1=1"  // Condición base para agregar filtros dinámicamente
        );

        // Agrega condiciones solo si los parámetros no son null
        if (dniPaciente != null && !dniPaciente.trim().isEmpty()) {
            sql.append(" AND t.dni_paciente = ?");
        }
        if (fecha != null) {
            sql.append(" AND t.fecha = ?");
        }
        if (estado != null && !estado.trim().isEmpty()) {
            sql.append(" AND t.estado = ?");
        }


        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (dniPaciente != null && !dniPaciente.trim().isEmpty()) {
                ps.setString(paramIndex++, dniPaciente);
            }
            if (fecha != null) {
                ps.setDate(paramIndex++, Date.valueOf(fecha));
            }
            if (estado != null && !estado.trim().isEmpty()) {
                ps.setString(paramIndex++, estado);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Turno turno = new Turno();
                turno.setIdTurno(rs.getInt("id_turno"));
                turno.setNombrePaciente(rs.getString("nombre_paciente"));
                turno.setNombreDoctor(rs.getString("nombre_doctor"));
                turno.setFecha(rs.getDate("fecha").toLocalDate());
                turno.setHora(rs.getTime("hora").toLocalTime());
                turno.setEstado(rs.getString("estado"));
                lista.add(turno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


    public boolean actualizarEstado(int idTurno, String nuevoEstado) {
        String sql = "UPDATE turnos SET estado = ? WHERE id_turno = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setInt(2, idTurno);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
