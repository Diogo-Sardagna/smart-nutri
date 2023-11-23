package Model;

public class PlanoAlimentar {
    private Paciente paciente;
    private String plano;

    public PlanoAlimentar(Paciente paciente, String plano) {
        this.paciente = paciente;
        this.plano = plano;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }
}
