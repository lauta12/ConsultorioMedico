package ConsultorioMedico.modelo;

public class Turno {
    private int id;
    private String dniPaciente;
    private String fecha;
    private String hora;
    private String motivo;
    private String estado;

    public Turno() {

    }

    // getters
    public int getId() { return id; }

    public String getDniPaciente() { return dniPaciente; }

    public String getEstado() { return estado; }

    public String getFecha() { return fecha; }

    public String getHora() { return hora; }

    public String getMotivo() { return motivo; }

    // setters

    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}

