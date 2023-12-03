package Fragment;

import com.example.smartnutri.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Controller.AgendamentoController;
import Model.Agendamento;
import Model.UsuarioLogado;
import Observador.ConsultaFragmentObserver;
import VisualComponent.AgendamentoRecyclerViewAdapter;

public class AprovarConsultaFragment extends Fragment implements ConsultaFragmentObserver {
    // Tela
    private View view;
    private FragmentManager fragmentManager;
    // Componentes visuais
    private RecyclerView rvAgendamentosSolicitados;
    // Controladores
    private AgendamentoController agendamentoController;
    // Modelos
    private UsuarioLogado usuarioLogado;
    // Listas
    private List<Agendamento> agendamentosPendentes;

    public AprovarConsultaFragment() { }

    public AprovarConsultaFragment(FragmentManager fragmentManager/*, AgendamentoController agendamentoController*/) {
        this.fragmentManager = fragmentManager;
//        this.agendamentoController = agendamentoController;
//        this.agendamentoController.setObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_aprovar_consulta, container, false);
        this.usuarioLogado = new UsuarioLogado();
        this.agendamentoController = new AgendamentoController(this, getContext());
        this.agendamentoController.setFragmentManager(this.fragmentManager);

        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        this.rvAgendamentosSolicitados = this.view.findViewById(R.id.rvAgendamentosSolicitados);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.view.getContext());
        this.rvAgendamentosSolicitados.setLayoutManager(layoutManager);
        try {
            this.agendamentoController.listarAgendamentosByNutricionista(usuarioLogado.getNutricionistaLogado());
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this.view.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void carregandoListaAgendamentos(AgendamentoRecyclerViewAdapter adapter) {
        this.rvAgendamentosSolicitados.setAdapter(adapter);
    }

    @Override
    public void carregandoAgendamentoSelecionado(Agendamento agendamento) {
        // SEM IMPLEMENTAÇÃO
    }
}
