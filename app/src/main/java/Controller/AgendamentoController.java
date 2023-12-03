package Controller;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.smartnutri.R;

import java.util.ArrayList;
import java.util.List;

import java.util.Calendar;

import Fragment.DetalhesAgendamentoFragment;
import Model.Agendamento;
import Model.AgendamentoSalvo;
import Model.Nutricionista;
import Model.Paciente;
import Observador.ConsultaControllerObserver;
import Observador.ConsultaFragmentObserver;
import Repository.AgendamentoRepository;
import VisualComponent.AgendamentoRecyclerViewAdapter;

public class AgendamentoController implements AgendamentoRepository, ConsultaControllerObserver {
    private AgendamentoSingleton agendamentoSingleton = AgendamentoSingleton.getInstance();

    private FragmentManager fragmentManager;
    private Context context;
    private ConsultaFragmentObserver fragmentObserver;
    private Agendamento agendamentoSelecionado;
    private AgendamentoSalvo agendamentoSalvo;

    public AgendamentoController(ConsultaFragmentObserver fragmentObserver, Context context) {
        this.fragmentObserver = fragmentObserver;
        this.context = context;
    }

    public void setFragmentManager(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    public void setObserver(ConsultaFragmentObserver observer){
        this.fragmentObserver = observer;
    }

    public void gravarAgendamento(Agendamento agendamento) throws Exception{
        this.agendamentoSalvo = new AgendamentoSalvo();
        try {
            if (agendamento == null) {
                this.fragmentObserver.showMessage("Agendamento null");
            }
            if (getAgendamentoByPaciente(agendamento.getPaciente()) != null) {
                this.fragmentObserver.showMessage("Agendamento já solicitado para a data " + agendamento.getDataFormatada());
            }
            insert(agendamento);
            agendamentoSalvo.setAgendamento(agendamento);
            this.fragmentObserver.showMessage("Agendamento solicitado para a data " + agendamento.getDataFormatada());
        } catch (Exception e){
            this.fragmentObserver.showMessage(e.getMessage());
        }
    }

    public void listarAgendamentosByNutricionista(Nutricionista nutricionista) throws Exception {
        try {
            List<Agendamento> listAgendamento = getAgendamentoByNutricionista(nutricionista);
            AgendamentoRecyclerViewAdapter adapterAgendamentos = new AgendamentoRecyclerViewAdapter(listAgendamento, this);
            this.fragmentObserver.carregandoListaAgendamentos(adapterAgendamentos);
            this.fragmentObserver.showMessage("Agendamentos carregados com sucesso!");
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

//    private boolean agendamentoDuplicado(Agendamento novoAgendamento) {
//        List<Agendamento> agendamentosExistentes = getAllAgendamentos();
//        for (Agendamento existente : agendamentosExistentes) {
//            if (existente.getPaciente().equals(novoAgendamento.getPaciente()) &&
//                    existente.getNutricionista().equals(novoAgendamento.getNutricionista())) {
//                return true; // Agendamento idêntico encontrado
//            }
//        }
//        return false; // Nenhum agendamento idêntico encontrado
//    }

    @Override
    public void insert(Agendamento agendamento) {
        agendamentoSingleton.adicionarAgendamento(agendamento);
    }

    @Override
    public List<Agendamento> getAllAgendamentos() {
        return agendamentoSingleton.getAgendamentos();
    }

    @Override
    public List<Agendamento> getAgendamentoByNutricionista(Nutricionista nutricionista) {
        this.agendamentoSalvo = new AgendamentoSalvo();
        List<Agendamento> result = new ArrayList<>();
        for (Agendamento agendamento : agendamentoSingleton.getAgendamentos()) {
            if (agendamento.getNutricionista().equals(nutricionista)) {
                result.add(agendamento);
            }
        }

        if (result == null || result.size() == 0) {
            if (agendamentoSalvo.getAgendamento() != null) {
                result.add(agendamentoSalvo.getAgendamento());
            } else {
                Nutricionista nutricionistaTeste = new Nutricionista("NomeNutricionista", "12345678901", "01/01/1980", "123456789", "n@email.com", "Dgs2002@", "12345");
                Paciente paciente = new Paciente("NomePaciente", "98765432109", "02/02/1990", "987654321", "paciente@email.com", "senha", 70.5, 1.75, "Masculino", "Informações adicionais");
                Calendar dataAgendamento = Calendar.getInstance();
                dataAgendamento.set(Calendar.YEAR, 2023);
                dataAgendamento.set(Calendar.MONTH, Calendar.DECEMBER); // Lembre-se que os meses começam do zero (janeiro é 0)
                dataAgendamento.set(Calendar.DAY_OF_MONTH, 15);// Você precisa definir a data desejada
                Boolean aprovado = false; // Defina se o agendamento está aprovado ou não

                Agendamento agendamento = new Agendamento(nutricionista, dataAgendamento, paciente, aprovado);
                result.add(agendamento);
            }
        }

        return result;
    }

    @Override
    public Agendamento getAgendamentoByPaciente(Paciente paciente) {
        for (Agendamento agendamento : agendamentoSingleton.getAgendamentos()) {
            if (agendamento.getPaciente().equals(paciente)) {
                return agendamento;
            }
        }
        return null;
    }

    @Override
    public List<Agendamento> getAgendamento(Boolean aprovado) {
        List<Agendamento> result = new ArrayList<>();
        for (Agendamento agendamento : agendamentoSingleton.getAgendamentos()) {
            if (agendamento.getAprovado().equals(aprovado)) {
                result.add(agendamento);
            }
        }
        return result;
    }

    @Override
    public void aprovarAgendamento(int agendamentoId) {
        // Encontrar o agendamento pelo ID
        Agendamento agendamento = findAgendamentoById(agendamentoId);

        // Verificar se o agendamento existe e ainda não foi aprovado
        if (agendamento != null && !agendamento.getAprovado()) {
            // Alterar o status para aprovado
            agendamento.setAprovado(true);
        }
    }

    // Método auxiliar para encontrar um agendamento pelo ID
    private Agendamento findAgendamentoById(int agendamentoId) {
        for (Agendamento agendamento : agendamentoSingleton.getAgendamentos()) {
            if (agendamento.getId() == agendamentoId) {
                return agendamento;
            }
        }
        return null;
    }

    @Override
    public void selecionaAgendamento(Agendamento agendamentoSelecionado) {
        this.agendamentoSelecionado = agendamentoSelecionado;
        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new DetalhesAgendamentoFragment(this.fragmentManager, this)).commitNow();
        this.fragmentObserver.carregandoAgendamentoSelecionado(agendamentoSelecionado);
    }
}
