package View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.PessoaController;
import Model.Pessoa;
import Repository.PessoaRepository;
import com.example.smartnutri.R;

public class CadastroActivity extends AppCompatActivity {

    private RadioButton radioPaciente;
    private RadioButton radioNutricionista;
    private EditText etNome;
    private EditText etCpf;
    private EditText etDataNascimento;
    private EditText etSexo;
    private EditText etPeso;
    private EditText etAltura;
    private EditText etCrm;
    private EditText etTelefone;
    private EditText etEmail;
    private EditText etSenha;
    private EditText etConfirmarSenha;
    private Button btnCadastrar;

    private PessoaController pessoaController;
    private PessoaRepository pessoaRepository;

    private List<Pessoa> pessoaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.pessoaController = new PessoaController();

        initComponents();
        initActions();
    }

    private void initActions() {
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String nome = etNome.getText().toString();
                    String email = etEmail.getText().toString();
                    String senha = etSenha.getText().toString();
                    String confirmar = etConfirmarSenha.getText().toString();

                    if (!isValidEmail(email)) {
                        showMessage("Informe um e-mail válido.");
                        return;
                    }

                    if (!isValidPassword(senha)) {
                        return;
                    }

                    if (!passwordConfirm(senha, confirmar)) {
                        return;
                    }

                    if (isValidInput(nome, email, senha)) {
                        // Crie um novo usuário e adicione-o à lista de usuários
                        Pessoa newUser = new Pessoa(nome, email, senha);
                        if (!pessoaRepository.insertPessoa(newUser);) {
                            showMessage("E-mail já cadastrado.");
                            return;
                        }

                        showMessage("Cadastro bem-sucedido.");
                        // Volte para a tela de login
                        finish();
                    } else {
                        showMessage("Preencha todos os campos corretamente.");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    // Por enquanto, para ser mais simples realizar o cadastro, apenas será necessário informar o nome, email e senha
    private boolean isValidInput(String nome, String email, String senha) {
        return !nome.trim().isEmpty() && !email.trim().isEmpty() && !senha.trim().isEmpty();
    }

    // Valida a confirmação da senha
    private boolean passwordConfirm(String senha, String confirma) {
        if (confirma.trim().isEmpty()) {
            showMessage("Confirme sua senha.");
            return false;
        }

        if (!senha.equals(confirma)) {
            showMessage("Confirmação incorreta.");
            return false;
        }

        return true;
    }

    // Verifica se a senha tem pelo menos 8 caracteres, uma letra maiúscula e um caractere especial
    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            showMessage("A senha deve conter no mínimo 8 caracteres.");
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            showMessage("A senha deve conter uma letra maiuscula.");
            return false;
        }

        if (!password.matches(".*[!@#$%^&*()].*")) {
            showMessage("A senha deve conter um caractere especial.");
            return false;
        }

        return password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[!@#$%^&*()].*");
    }

    // Valida E-mail
    private boolean isValidEmail(String email) {
        // Use uma expressão regular para validar o formato do e-mail
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        // Crie um objeto Pattern com a expressão regular
        Pattern pattern = Pattern.compile(emailPattern);

        // Crie um objeto Matcher para comparar o e-mail com a expressão regular
        Matcher matcher = pattern.matcher(email);

        // Verifique se o e-mail corresponde à expressão regular
        return matcher.matches();
    }

    // Função para exibir uma mensagem
    public void showMessage(String msg) {
        Toast.makeText(CadastroActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    // Inicializa os componentes
    private void initComponents() {
        radioPaciente = findViewById(R.id.radioPaciente);
        radioNutricionista = findViewById(R.id.radioNutricionista);

        etNome = findViewById(R.id.etNome);
        etCpf = findViewById(R.id.etCpf);
        etDataNascimento = findViewById(R.id.etDataNascimento);

        //Paciente
        etSexo = findViewById(R.id.etSexo);
        etPeso = findViewById(R.id.etPeso);
        etAltura = findViewById(R.id.etAltura);

        //Nutricionista
        etCrm = findViewById(R.id.etCrm);

        etTelefone = findViewById(R.id.etTelefone);
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        etConfirmarSenha = findViewById(R.id.etConfirmarSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
    }
}