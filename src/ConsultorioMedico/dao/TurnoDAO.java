package ConsultorioMedico.dao;

import ConsultorioMedico.modelo.Turno;
import ConsultorioMedico.util.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Statement;
import java.sql.Time;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class TurnoDAO {
    // guardar
    public boolean guardarTurno(Turno turno) {
        String sql = "INSERT INTO turnos (dni_paciente, id_doctor, fecha, hora) VALUES (?,?,?,?)";

        try(Connection conn = Conexion.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, turno.getDniPaciente());
            stmt.setInt(2, turno.getIdDoctor());
            stmt.setDate(3, Date.valueOf(turno.getFecha()));
            stmt.setTime(4, Time.valueOf(turno.getHora()));

            stmt.executeUpdate();

            return true;

        } catch(SQLException e) {
            e.printStackTrace();

            return false;
        }
    }

    //leer
    public List<Turno> obtenerTurnos() {
        List<Turno> lista = new ArrayList<>();
        String sql = "SELECT * FROM turnos ORDER BY fecha, hora";

        try(Connection conn = Conexion.getConexion();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

            while(rs.next()) {
                Turno t = new Turno();
                t.setIdTurno(rs.getInt("id_turno"));
                t.setDniPaciente(rs.getString("dni_paciente"));
                t.setIdDoctor(rs.getInt("id_doctor"));
                t.setFecha(rs.getDate("fecha").toLocalDate());
                t.setHora(rs.getTime("hora").toLocalTime());
                lista.add(t);
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

    // leer
    public List<Turno> obtenerTurnosPorDoctor(int idDoctor) {
        List<Turno> lista = new ArrayList<>();
        String sql = "SELECT * FROM turnos WHERE id_doctor = ? ORDER BY fecha, hora";

        try(Connection conn = Conexion.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idDoctor);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Turno t = new Turno();
                t.setIdTurno(rs.getInt("id_turno"));
                t.setDniPaciente(rs.getString("dni_paciente"));
                t.setIdDoctor(rs.getInt("id_doctor"));
                t.setFecha(rs.getDate("fecha").toLocalDate());
                t.setHora(rs.getTime("hora").toLocalTime());
                lista.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

}
