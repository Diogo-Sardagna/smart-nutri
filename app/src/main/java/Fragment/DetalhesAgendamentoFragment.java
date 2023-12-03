package Fragment;

import java.text.DecimalFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.smartnutri.R;

import java.io.Serializable;

import Controller.AgendamentoController;
import Model.Agendamento;
import Observador.ConsultaFragmentObserver;
import VisualComponent.AgendamentoRecyclerViewAdapter;

public class DetalhesAgendamentoFragment extends Fragment implements ConsultaFragmentObserver {
    // Tela
    private View view;
    private FragmentManager fragmentManager;
    // Componentes visuais
    private TextView textViewDetalhes;
    private Button btnAprovar;
    // Controladores
    private AgendamentoController agendamentoController;
    // Modelos
    private Agendamento agendamento;

    public DetalhesAgendamentoFragment() { }

    public DetalhesAgendamentoFragment(FragmentManager fragmentManager, AgendamentoController agendamentoController) {
        this.fragmentManager = fragmentManager;
        this.agendamentoController = agendamentoController;
        this.agendamentoController.setObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_detalhes_agendamento, container, false);
//        this.agendamentoController = new AgendamentoController(this, getContext());
//        this.agendamentoController.setFragmentManager(this.fragmentManager);
        initComponents(view);
        initActions();
        return view;
    }

    private void initComponents(View view) {
        this.textViewDetalhes = this.view.findViewById(R.id.textViewDetalhes);
        this.btnAprovar = this.view.findViewById(R.id.btnAprovar);
    }

    private void initActions() {
        btnAprovar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agendamentoController.aprovarAgendamento(agendamento.getId());
                showMessage("Agendamento aprovado!");
            }
        });
    }

    public static String calcularIMC(double peso, double altura) {
        // Verificar se os parâmetros são válidos
        if (peso <= 0 || altura <= 0) {
            return "Valores inválidos para peso ou altura.";
        }

        // Calcular o IMC
        double imc = peso / (altura * altura);

        // Interpretar o IMC e fornecer uma mensagem informativa
        String resultado;
        if (imc < 18.5) {
            resultado = "Abaixo do peso";
        } else if (imc < 24.9) {
            resultado = "Peso normal";
        } else if (imc < 29.9) {
            resultado = "Sobrepeso";
        } else if (imc < 34.9) {
            resultado = "Obesidade grau 1";
        } else if (imc < 39.9) {
            resultado = "Obesidade grau 2";
        } else {
            resultado = "Obesidade grau 3";
        }

        // Construir a string informativa
        return String.format("O IMC é %.2f.\nClassificação: %s.", imc, resultado);
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
        if (agendamento != null) {
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            double peso = agendamento.getPaciente().getPeso();
            double altura = agendamento.getPaciente().getAltura();

            // Exiba informações detalhadas
            String detalhes =
                    "\n* SOLICITAÇÃO DE AGENDAMENTO *\n \n"
                            + "ID: " + agendamento.getId() + "\n"
                            + "Data: " + agendamento.getDataFormatada()

                            + "\n \n* INFORMAÇÕES DO PACIENTE *\n \n"
                            + "Paciente: " + agendamento.getPaciente().getNome() + "\n"
                            + "CPF: " + agendamento.getPaciente().getCpf() + "\n"
                            + "Data de Nascimento: " + agendamento.getPaciente().getDataNascimento() + "\n"
                            + "Sexo: " + agendamento.getPaciente().getSexo() + "\n"
                            + "Peso: " + decimalFormat.format(peso) + " kg\n"
                            + "Altura: " + decimalFormat.format(altura) + " m\n"
                            + "Informações adicionais: " + agendamento.getPaciente().getInformacoes()

                            + "\n \n* CONTATOS *\n \n"
                            + "Telefone: (47) 9" + agendamento.getPaciente().getTelefone() + "\n"
                            + "E-mail" + agendamento.getPaciente().getEmail()

                            + "\n \n* IMC *\n \n"
                            + calcularIMC(peso, altura) + "\n";

            textViewDetalhes.setText(detalhes);
        }
    }
}
