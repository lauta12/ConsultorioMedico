import ConsultorioMedico.controlador.ConsultorioMedicoControlador;
import ConsultorioMedico.vista.ConsultorioMedicoVista;

public class Main {

    public static void main(String[] args) {
        ConsultorioMedicoVista vista = new ConsultorioMedicoVista();
        ConsultorioMedicoControlador controlador = new ConsultorioMedicoControlador(vista);
        controlador.iniciar();
    }

}