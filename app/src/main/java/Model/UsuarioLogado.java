package Model;

public class UsuarioLogado {
    private static Paciente pacienteLogado;
    private static Nutricionista nutricionistaLogado;

    public Paciente getPacienteLogado() {
        return pacienteLogado;
    }

    public void setPacienteLogado(Paciente paciente) {
        pacienteLogado = paciente;
    }

    public Nutricionista getNutricionistaLogado() {
        return nutricionistaLogado;
    }

    public void setNutricionistaLogado(Nutricionista nutricionista) {
        nutricionistaLogado = nutricionista;
    }
}
