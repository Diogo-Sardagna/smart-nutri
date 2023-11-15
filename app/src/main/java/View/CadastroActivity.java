package View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.PessoaController;
import Model.Pessoa;
import Model.Paciente;
import Model.Nutricionista;
import Repository.PessoaRepository;
import Repository.PacienteRepository;
import Repository.NutricionistaRepository;
import com.example.smartnutri.R;

public class CadastroActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioPaciente;
    private RadioButton radioNutricionista;
    private EditText etNome;
    private EditText etCpf;
    private EditText etDataNascimento;
    private EditText etSexo;
    private EditText etPeso;
    private EditText etAltura;
    private EditText etInformacoesAdicionais;
    private EditText etCrm;
    private EditText etTelefone;
    private EditText etEmail;
    private EditText etSenha;
    private EditText etConfirmarSenha;
    private Button btnCadastrar;

//    private PessoaController pessoaController;
    private PessoaRepository pessoaRepository;
    private PacienteRepository pacienteRepository;
    private NutricionistaRepository nutricionistaRepository;

    private List<Pessoa> pessoaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.pessoaRepository = new PessoaRepository(this);
        this.pacienteRepository = new PacienteRepository(this);
        this.nutricionistaRepository = new NutricionistaRepository(this);

        initComponents();
        initActions();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Resetar a visibilidade de todos os campos
                setCamposVisiveis(View.VISIBLE);
                limparCampos();

                // Verificar qual RadioButton está selecionado
                if (checkedId == R.id.radioPaciente) {
                    // Se Paciente está selecionado, esconder o campo CRM
                    etCrm.setVisibility(View.GONE);
                } else if (checkedId == R.id.radioNutricionista) {
                    // Se Nutricionista está selecionado, esconder os campos Sexo, Peso, Altura, Informações Adicionais
                    etSexo.setVisibility(View.GONE);
                    etPeso.setVisibility(View.GONE);
                    etAltura.setVisibility(View.GONE);
                    etInformacoesAdicionais.setVisibility(View.GONE);
                }

                ajustarPosicaoCampos();
            }
        });
    }

    private void initActions() {
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String nome = etNome.getText().toString();
                    String cpf = etCpf.getText().toString();
                    String dataNascimento = etDataNascimento.getText().toString();
                    String sexo = etSexo.getText().toString();
                    String peso = etPeso.getText().toString();
                    String altura = etAltura.getText().toString();
                    String informacao = etInformacoesAdicionais.getText().toString();
                    String crm = etCrm.getText().toString();
                    String telefone = etTelefone.getText().toString();
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

                        if (radioGroup.getCheckedRadioButtonId() == R.id.radioPaciente) {
                            // Se Paciente está selecionado, criar uma instância de Paciente
                            Paciente paciente = new Paciente(nome, cpf, dataNascimento, telefone, email, senha,
                                    Double.parseDouble(peso), Double.parseDouble(altura), sexo, informacao);

                            if (pacienteRepository.getPacienteByEmail(paciente.getEmail()) != null) {
                                showMessage("E-mail " + paciente.getEmail() + " já cadastrado.");
                                return;
                            }

                            if (pacienteRepository.getPacienteByCpf(paciente.getCpf()) != null) {
                                showMessage("CPF " + paciente.getCpf() + " já cadastrado.");
                                return;
                            }

                            pacienteRepository.insertPaciente(paciente);
                            showMessage("Cadastro do Paciente bem-sucedido.");;
                        } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioNutricionista) {
                            Nutricionista nutricionista = new Nutricionista(nome, cpf, dataNascimento, telefone, email, senha, crm);

                            if (nutricionistaRepository.getNutricionistaByEmail(nutricionista.getEmail()) != null) {
                                showMessage("E-mail " + nutricionista.getEmail() + " já cadastrado.");
                                return;
                            }

                            if (nutricionistaRepository.getNutricionistaByCpf(nutricionista.getCpf()) != null) {
                                showMessage("CPF " + nutricionista.getCpf() + " já cadastrado.");
                                return;
                            }

                            nutricionistaRepository.insertNutricionista(nutricionista);
                            showMessage("Cadastro do Nutricionista bem-sucedido.");
                        }

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

    // Método para ajustar a posição dos campos
    private void ajustarPosicaoCampos() {
        RelativeLayout.LayoutParams params;

        // Se o campo CRM estiver visível, ajuste a posição dos campos seguintes
        if (etCrm.getVisibility() == View.VISIBLE) {
            params = (RelativeLayout.LayoutParams) etTelefone.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, R.id.etCrm);

            params = (RelativeLayout.LayoutParams) etInformacoesAdicionais.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, R.id.etTelefone);
        } else { // Se o campo CRM estiver invisível, ajuste a posição dos campos seguintes
            params = (RelativeLayout.LayoutParams) etTelefone.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, R.id.etSexo);

            params = (RelativeLayout.LayoutParams) etInformacoesAdicionais.getLayoutParams();
            params.addRule(RelativeLayout.BELOW, R.id.etTelefone);
        }
    }

    // Método para definir a visibilidade dos campos
    private void setCamposVisiveis(int visibility) {
        etNome.setVisibility(visibility);
        etCpf.setVisibility(visibility);
        etDataNascimento.setVisibility(visibility);
        etSexo.setVisibility(visibility);
        etPeso.setVisibility(visibility);
        etAltura.setVisibility(visibility);
        etCrm.setVisibility(visibility);
        etTelefone.setVisibility(visibility);
        etInformacoesAdicionais.setVisibility(visibility);
        etEmail.setVisibility(visibility);
        etSenha.setVisibility(visibility);
        etConfirmarSenha.setVisibility(visibility);
    }

    // Método para limpar os campos
    private void limparCampos() {
        etNome.setText("");
        etCpf.setText("");
        etDataNascimento.setText("");
        etSexo.setText("");
        etPeso.setText("");
        etAltura.setText("");
        etCrm.setText("");
        etTelefone.setText("");
        etInformacoesAdicionais.setText("");
        etEmail.setText("");
        etSenha.setText("");
        etConfirmarSenha.setText("");
    }

    // Função para exibir uma mensagem
    public void showMessage(String msg) {
        Toast.makeText(CadastroActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    // Inicializa os componentes
    private void initComponents() {
        //Radio
        radioGroup = findViewById(R.id.radioGroup);
        radioPaciente = findViewById(R.id.radioPaciente);
        radioNutricionista = findViewById(R.id.radioNutricionista);

        etNome = findViewById(R.id.etNome);
        etCpf = findViewById(R.id.etCpf);
        etDataNascimento = findViewById(R.id.etDataNascimento);

        //Paciente
        etSexo = findViewById(R.id.etSexo);
        etPeso = findViewById(R.id.etPeso);
        etAltura = findViewById(R.id.etAltura);
        etInformacoesAdicionais = findViewById(R.id.etInformacoesAdicionais);

        //Nutricionista
        etCrm = findViewById(R.id.etCrm);

        etTelefone = findViewById(R.id.etTelefone);
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        etConfirmarSenha = findViewById(R.id.etConfirmarSenha);

        //Button
        btnCadastrar = findViewById(R.id.btnCadastrar);
    }
}
