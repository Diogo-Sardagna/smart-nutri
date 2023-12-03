package Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.smartnutri.R;

import java.util.Calendar;
import java.util.List;

import Controller.AgendamentoController;
import Model.Agendamento;
import Model.Nutricionista;
import Model.UsuarioLogado;
import Observador.ConsultaFragmentObserver;
import Repository.NutricionistaRepository;
import VisualComponent.NutricionistaAdapter;
import VisualComponent.AgendamentoRecyclerViewAdapter;

public class AgendarConsultaFragment extends Fragment implements ConsultaFragmentObserver {
    // Tela
    private View view;
    private FragmentManager fragmentManager;
    // Componentes visuais
    private TextView dataSelecionadaTextView;
    private Button selecionarDataButton;
    private Button btnAgendarConsulta;
    private Spinner nutricionistaSpinner;
    private Calendar calendar;
    // Modelos
    private Agendamento agendamento;
    private UsuarioLogado usuarioLogado;
    // Controladores
    private AgendamentoController agendamentoController;
    // Repositórios
    private NutricionistaRepository nutricionistaRepository;
    private NutricionistaAdapter nutricionistaAdapter;
    // Listas
    private List<Nutricionista> nutricionistas;

    public AgendarConsultaFragment() { }

    public AgendarConsultaFragment(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_agendar_consulta, container, false);

        this.nutricionistaRepository = new NutricionistaRepository(getContext());
        this.agendamentoController = new AgendamentoController(this, getContext());
        this.usuarioLogado = new UsuarioLogado();
        initComponents(view);
        initSpinnerOptions();
        initActions();
        return view;
    }

    private void initSpinnerOptions() {
        try {
            this.nutricionistas = nutricionistaRepository.getAllNutricionistas();
            if (nutricionistas != null && !nutricionistas.isEmpty()) {
                this.nutricionistaAdapter = new NutricionistaAdapter(this.view.getContext(), nutricionistas);
                nutricionistaSpinner.setAdapter(nutricionistaAdapter);
                nutricionistaSpinner.setSelection(0);
            } else {
                showMessage("Nenhum nutricionista cadastrado.");
            }
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    private void initActions() {
        calendar = Calendar.getInstance();
        this.agendamento = new Agendamento((Nutricionista) nutricionistaSpinner.getSelectedItem(), calendar, usuarioLogado.getPacienteLogado(), false);
        dataSelecionadaTextView.setText(agendamento.getDataFormatada());

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);

                if (selectedDate.before(Calendar.getInstance())) {
                    showMessage("Selecione uma data igual ou superior à data atual.");
                } else if (selectedDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                        selectedDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    showMessage("Não é permitido agendar para sábado ou domingo.");
                } else {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    agendamento = new Agendamento((Nutricionista) nutricionistaSpinner.getSelectedItem(), calendar, usuarioLogado.getPacienteLogado(), false);

                    dataSelecionadaTextView.setText(agendamento.getDataFormatada());
                }
            }
        };

        selecionarDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(
                        requireContext(),
                        dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });

        btnAgendarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    agendamentoController.gravarAgendamento(agendamento);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    private void initComponents(View view) {
        dataSelecionadaTextView = view.findViewById(R.id.dataSelecionadaTextView);
        selecionarDataButton = view.findViewById(R.id.selecionarDataButton);
        nutricionistaSpinner = view.findViewById(R.id.nutricionistaSpinner);
        btnAgendarConsulta = view.findViewById(R.id.btnAgendar);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void carregandoListaAgendamentos(AgendamentoRecyclerViewAdapter adapter) {
        // SEM IMPLEMENTAÇÃO
    }

    @Override
    public void carregandoAgendamentoSelecionado(Agendamento agendamento) {
        // SEM IMPLEMENTAÇÃO
    }
}
