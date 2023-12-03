package Observador;

import Model.Agendamento;
import VisualComponent.AgendamentoRecyclerViewAdapter;

public interface ConsultaFragmentObserver {
    void showMessage(String msg);
    void carregandoListaAgendamentos(AgendamentoRecyclerViewAdapter adapter);
    void carregandoAgendamentoSelecionado(Agendamento agendamento);
}
