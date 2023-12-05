package View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

//import Repository.PessoaRepository;
import Model.Nutricionista;
import Model.Paciente;
import Model.Pessoa;
import Model.UsuarioLogado;
import Repository.NutricionistaRepository;
import Repository.PacienteRepository;

import com.example.smartnutri.R;

public class LoginActivity extends AppCompatActivity {

    // Componentes Visuais
    public EditText edtxEmail;
    public EditText edtxSenha;
    private Button btnEntrar;
    private Button btnCadastrar;

    // Repositórios
    public PacienteRepository pacienteRepository;
    public NutricionistaRepository nutricionistaRepository;

    // Modelos
    private UsuarioLogado usuarioLogado;

    // Listas
    private List<Paciente> pacientesList;
    private List<Nutricionista> nutricionistasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.pacienteRepository = new PacienteRepository(this);
        this.nutricionistaRepository = new NutricionistaRepository(this);
        this.usuarioLogado = new UsuarioLogado();

        initComponents();
        initActions();
    }

    public void initActions() {
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String email = edtxEmail.getText().toString();
                    String senha = edtxSenha.getText().toString();

                    if (login(email, senha) == 1) {
                        // Autenticação bem-sucedida, vá para a próxima atividade
                        Intent intent = new Intent(LoginActivity.this, PacienteMenuActivity.class);
                        Paciente paciente = pacienteRepository.getPacienteByEmail(email);

                        // Verifique se usuarioLogado não é nulo antes de chamar setPacienteLogado
                        if (usuarioLogado != null) {
                            usuarioLogado.setPacienteLogado(paciente);
                        } else {
                            // Trate o caso em que usuarioLogado é nulo
                            showMessage("Erro: usuarioLogado é nulo.");
                            return;  // ou tome outra ação apropriada
                        }

                        showMessage("Paciente logado.");
                        startActivity(intent);
                    } else if (login(email, senha) == 2) {
                        // Autenticação bem-sucedida, vá para a próxima atividade
                        Intent intent = new Intent(LoginActivity.this, NutricionistaMenuActivity.class);
                        Nutricionista nutricionista = nutricionistaRepository.getNutricionistaByEmail(email);

                        if (usuarioLogado != null) {
                            usuarioLogado.setNutricionistaLogado(nutricionista);
                        } else {
                            // Trate o caso em que usuarioLogado é nulo
                            showMessage("Erro: usuarioLogado é nulo.");
                            return;  // ou tome outra ação apropriada
                        }

                        showMessage("Nutricionista logado.");
                        startActivity(intent);
                    } else {
                        showMessage("Login falhou. Verifique suas credenciais.");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abra a tela de cadastro
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }

    // Função para verificar as credenciais de login
    public int login(String email, String senha) throws Exception {
        pacientesList = pacienteRepository.getAllPacientes();
        for (Paciente user : pacientesList) {
            if (user.getEmail().equals(email) && user.getSenha().equals(senha)) {
                return 1;
            }
        }

        nutricionistasList = nutricionistaRepository.getAllNutricionistas();
        for (Nutricionista user : nutricionistasList) {
            if (user.getEmail().equals(email) && user.getSenha().equals(senha)) {
                return 2;
            }
        }

        return 0;
    }

    // Função para exibir uma mensagem
    public void showMessage(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void initComponents() {
        edtxEmail = findViewById(R.id.editTextEmail);
        edtxSenha = findViewById(R.id.editTextSenha);
        btnEntrar = findViewById(R.id.buttonEntrar);
        btnCadastrar = findViewById(R.id.buttonCadastrar);
    }
}