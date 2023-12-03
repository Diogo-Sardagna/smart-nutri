package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Agendamento;

public class AgendamentoSingleton {

    private static AgendamentoSingleton instance;
    private List<Agendamento> agendamentos;

    private AgendamentoSingleton() {
        agendamentos = new ArrayList<>();
    }

    public static synchronized AgendamentoSingleton getInstance() {
        if (instance == null) {
            instance = new AgendamentoSingleton();
        }
        return instance;
    }

    public void adicionarAgendamento(Agendamento agendamento) {
        agendamentos.add(agendamento);
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }
}

