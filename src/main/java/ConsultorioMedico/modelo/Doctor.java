package ConsultorioMedico.modelo;

public class Doctor {
    private int id_doctor;
    private String nombre;
    private String especialidad;
    private String telefono;
    private String email;

    public Doctor() {}

    public Doctor(int id, String nombre, String especialidad, String telefono, String email) {
        this.id_doctor = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
    }

    @Override
    public String toString() {
        return nombre + " - " + especialidad;
    }

    // getters y setters
    public int getId_doctor() { return id_doctor; }
    public String getNombre() { return nombre; }
    public String getEspecialidad() { return especialidad; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }

    public void setId_doctor(int id_doctor) { this.id_doctor = id_doctor; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setEmail(String email) { this.email = email; }
}
