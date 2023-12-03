package Model;

public class AgendamentoSalvo {

    private static Agendamento agendamento;

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        AgendamentoSalvo.agendamento = agendamento;
    }
}
