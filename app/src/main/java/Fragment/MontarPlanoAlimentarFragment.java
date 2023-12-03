package Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.smartnutri.R;

import java.util.Calendar;

import Controller.PlanoAlimentarController;
import Model.Agendamento;
import Model.Nutricionista;
import Model.Paciente;
import Model.PlanoAlimentar;
import Repository.PlanoAlimentarRepository;

public class MontarPlanoAlimentarFragment extends Fragment {
    // Tela
    private View view;
    // Componentes visuais
    private TextView textViewDataAgendamento;
    private TextView textViewPaciente;
    private TextView textViewNutricionista;
    private EditText editTextPlanoAlimentar;
    private Button buttonConcluir;
    // Modelos
    private Agendamento agendamento;
    private PlanoAlimentar planoAlimentar;
    // Repositórios
    private PlanoAlimentarRepository planoAlimentarRepository;
    // Controladores
    private PlanoAlimentarController planoAlimentarController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_montar_plano_alimentar, container, false);
//        this.planoAlimentarRepository = new PlanoAlimentarRepository(this.view.getContext());
        this.planoAlimentarController = new PlanoAlimentarController(this.view.getContext());
        initComponents();
        initActions();

        if (agendamento == null) {
//            showMessage("Nenhum agendamento no momento.");
            Nutricionista nutricionista = new Nutricionista("Diogo", "12345678901", "01/01/1980", "123456789", "n@email.com", "Dgs2002@", "12345");
            Paciente paciente = new Paciente("Débora", "98765432109", "02/02/1990", "987654321", "paciente@email.com", "Dgs2002@", 70.5, 1.75, "Masculino", "Informações adicionais");
            Calendar dataAgendamento = Calendar.getInstance();
            dataAgendamento.set(Calendar.YEAR, 2023);
            dataAgendamento.set(Calendar.MONTH, Calendar.DECEMBER); // Lembre-se que os meses começam do zero (janeiro é 0)
            dataAgendamento.set(Calendar.DAY_OF_MONTH, 15);// Você precisa definir a data desejada
            Boolean aprovado = false; // Defina se o agendamento está aprovado ou não
            this.agendamento = new Agendamento(nutricionista, dataAgendamento, paciente, aprovado);
        }

        if (agendamento != null) {
            textViewDataAgendamento.setText("Data do Agendamento: " + agendamento.getDataFormatada());
            textViewPaciente.setText("Paciente: " + agendamento.getPaciente().getNome());
            textViewNutricionista.setText("Nutricionista: " + agendamento.getNutricionista().getNome());
        }

        return view;
    }

    private void initComponents() {
        this.textViewDataAgendamento = view.findViewById(R.id.textViewDataAgendamento);
        this.textViewPaciente = view.findViewById(R.id.textViewPaciente);
        this.textViewNutricionista = view.findViewById(R.id.textViewNutricionista);
        this.editTextPlanoAlimentar = view.findViewById(R.id.editTextPlanoAlimentar);
        this.buttonConcluir = view.findViewById(R.id.buttonConcluir);
    }

    private void initActions() {
        // Lógica do botão Concluir (substitua com sua própria lógica)
        buttonConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String plano = String.valueOf(editTextPlanoAlimentar.getText());
                    if (plano.trim().isEmpty()) {
                        showMessage("Monte o plano alimentar.");
                    }
                    planoAlimentar = new PlanoAlimentar(agendamento, plano);
//                    planoAlimentarRepository.salvarPlanoAlimentar(planoAlimentar);
                    planoAlimentarController.salvarPlanoAlimentar(planoAlimentar);
                    showMessage("Plano alimentar criado com sucesso!");
                } catch (Exception e) {
                    showMessage(e.getMessage());
                }
            }
        });
    }

    public void showMessage(String msg) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
