package ConsultorioMedico.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turno {
    private int idTurno;
    private int idDoctor;
    private String dniPaciente;
    private LocalDate fecha;
    private LocalTime hora;

    public Turno() {}

    public Turno(String dniPaciente, int idDoctor, LocalDate fecha, LocalTime hora) {
        this.dniPaciente = dniPaciente;
        this.idDoctor = idDoctor;
        this.fecha = fecha;
        this.hora = hora;
    }

    // getters
    public int getIdDoctor() { return idDoctor; }
    public int getIdTurno() { return idTurno; }
    public String getDniPaciente() { return dniPaciente; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHora() { return hora; }

    // setters
    public void setDniPaciente(String dniPaciente) { this.dniPaciente = dniPaciente; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public void setIdDoctor(int idDoctor) { this.idDoctor = idDoctor; }
    public void setIdTurno(int idTurno) { this.idTurno = idTurno; }
}

