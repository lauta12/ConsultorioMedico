package ConsultorioMedico.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {
    private int idTurno;
    private int idDoctor;
    private String dniPaciente;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private String nombrePaciente;
    private String nombreDoctor;

    public Turno() {}

    public Turno(String dniPaciente, int idDoctor, LocalDate fecha, LocalTime hora) {
        this.dniPaciente = dniPaciente;
        this.idDoctor = idDoctor;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = "Pendiente";
    }

    // Getters
    public int getIdTurno() { return idTurno; }
    public int getIdDoctor() { return idDoctor; }
    public String getDniPaciente() { return dniPaciente; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }
    public String getEstado() { return estado; }
    public String getNombrePaciente() { return nombrePaciente; }
    public String getNombreDoctor() { return nombreDoctor; }

    // Setters
    public void setIdTurno(int idTurno) { this.idTurno = idTurno; }
    public void setIdDoctor(int idDoctor) { this.idDoctor = idDoctor; }
    public void setDniPaciente(String dniPaciente) { this.dniPaciente = dniPaciente; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setNombrePaciente(String nombrePaciente) { this.nombrePaciente = nombrePaciente; }
    public void setNombreDoctor(String nombreDoctor) { this.nombreDoctor = nombreDoctor; }
}
