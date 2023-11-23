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

import android.text.InputFilter;
import android.text.Spanned;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Controller.PessoaController;
import Model.Pessoa;
import Model.Paciente;
import Model.Nutricionista;
//import Repository.PessoaRepository;
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
//    private PessoaRepository pessoaRepository;
    private PacienteRepository pacienteRepository;
    private NutricionistaRepository nutricionistaRepository;

    private List<Pessoa> pessoaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
//        this.pessoaRepository = new PessoaRepository(this);
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
            }
        });

        // Adicione um InputFilter para limitar a entrada a 2 casas decimais
        etAltura.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(1, 2)});
        etPeso.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(3, 2)});

//        addUnidadeMedida(etAltura, "cm");
//        addUnidadeMedida(etPeso, "kg");
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

                            if (!validaDuplicadoEmailOrCpf(paciente.getEmail(), paciente.getCpf())) {
                                return;
                            }

                            pacienteRepository.insertPaciente(paciente);
                            showMessage("Cadastro do Paciente bem-sucedido.");
                        } else if (radioGroup.getCheckedRadioButtonId() == R.id.radioNutricionista) {
                            Nutricionista nutricionista = new Nutricionista(nome, cpf, dataNascimento, telefone, email, senha, crm);

                            if (!validaDuplicadoEmailOrCpf(nutricionista.getEmail(), nutricionista.getCpf())) {
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
//    private void ajustarPosicaoCampos() {
//        RelativeLayout.LayoutParams params;
//
//        // Se o campo CRM estiver visível, ajuste a posição dos campos seguintes
//        if (etCrm.getVisibility() == View.VISIBLE) {
//            params = (RelativeLayout.LayoutParams) etTelefone.getLayoutParams();
//            params.addRule(RelativeLayout.BELOW, R.id.etCrm);
//
//            params = (RelativeLayout.LayoutParams) etInformacoesAdicionais.getLayoutParams();
//            params.addRule(RelativeLayout.BELOW, R.id.etTelefone);
//        } else { // Se o campo CRM estiver invisível, ajuste a posição dos campos seguintes
//            params = (RelativeLayout.LayoutParams) etTelefone.getLayoutParams();
//            params.addRule(RelativeLayout.BELOW, R.id.etSexo);
//
//            params = (RelativeLayout.LayoutParams) etInformacoesAdicionais.getLayoutParams();
//            params.addRule(RelativeLayout.BELOW, R.id.etTelefone);
//        }
//    }

    // Valida E-mail ou Cpf Duplicados
    private boolean validaDuplicadoEmailOrCpf(String email, String cpf) throws Exception {
        if (pacienteRepository.getPacienteByEmail(email) != null) {
            showMessage("E-mail " + email + " já cadastrado como Paciente.");
            return false;
        }

        if (pacienteRepository.getPacienteByCpf(cpf) != null) {
            showMessage("CPF " + cpf + " já cadastrado como Paciente.");
            return false;
        }

        if (nutricionistaRepository.getNutricionistaByEmail(email) != null) {
            showMessage("E-mail " + email + " já cadastrado como Nutricionista.");
            return false;
        }

        if (nutricionistaRepository.getNutricionistaByCpf(cpf) != null) {
            showMessage("CPF " + cpf + " já cadastrado como Nutricionista.");
            return false;
        }

        return true;
    }

    // Classe para limitar o número de casas decimais
    private static class DecimalDigitsInputFilter implements InputFilter {
        private final int digitsBeforeZero;
        private final int digitsAfterZero;

        DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
            this.digitsBeforeZero = digitsBeforeZero;
            this.digitsAfterZero = digitsAfterZero;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            StringBuilder builder = new StringBuilder(dest);
            builder.replace(dstart, dend, source.subSequence(start, end).toString());
            String text = builder.toString();

            // Verifica se o texto segue o padrão desejado
            if (!isValid(text)) {
                return "";
            }

            // Adiciona automaticamente o ponto decimal quando atinge o limite de dígitos antes do ponto
            if (text.indexOf('.') == -1 && text.length() == digitsBeforeZero) {
                builder.append(".00");
                return builder.subSequence(dstart, builder.length());
            }

//            // Remove o ponto decimal se estiver sendo apagado
//            if (dstart > 0 && text.charAt(dstart - 1) == '.' && text.indexOf('.') == dstart) {
//                builder.deleteCharAt(dstart - 1);
//                return builder.subSequence(dstart - 1, builder.length() - 1);
//            }

            return null;
        }

        // Verifica se o texto segue o padrão desejado
        private boolean isValid(String text) {
            String[] parts = text.split("\\.");
            if (parts.length > 1) {
                // Se há parte decimal, verifica o número de dígitos antes e depois do ponto
                return parts[0].length() <= digitsBeforeZero && parts[1].length() <= digitsAfterZero;
            } else {
                // Se não há parte decimal, verifica apenas o número de dígitos antes do ponto
                return true;
            }
        }
//        private final Pattern pattern;
//
//        DecimalDigitsInputFilter(int digitsAfterZero) {
//            pattern = Pattern.compile("[0-9]+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?");
//        }
//
//        @Override
//        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//            Matcher matcher = pattern.matcher(dest);
//            if (!matcher.matches())
//                return "";
//            return null;
//        }
    }

//    private void addUnidadeMedida(final EditText campo, final String und) {
//        // Referencie o EditText no código
////        final EditText etAltura = findViewById(R.id.etAltura);
//
//        // Adicione um TextWatcher para adicionar a unidade de medida
//        campo.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                // Não é necessário implementar neste caso
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                // Não é necessário implementar neste caso
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                campo.removeTextChangedListener(this);
//
//                String text = editable.toString();
//
//                if (text.isEmpty()) {
//                    // Se o texto está vazio, não exibe a unidade de medida
//                    campo.setText("");
//                } else if (!text.endsWith(und)) {
//                    // Adiciona a unidade de medida apenas se não estiver presente
//                    text += (" " + und);
//                    campo.setText(text);
//                    campo.setSelection(text.length());
//                }
//
//                campo.addTextChangedListener(this);
//            }
//        });
//    }

    // Método para definir a visibilidade dos campos
    private void setCamposVisiveis(int visibility) {
//        etNome.setVisibility(visibility);
//        etCpf.setVisibility(visibility);
//        etDataNascimento.setVisibility(visibility);
        etSexo.setVisibility(visibility);
        etPeso.setVisibility(visibility);
        etAltura.setVisibility(visibility);
        etCrm.setVisibility(visibility);
//        etTelefone.setVisibility(visibility);
        etInformacoesAdicionais.setVisibility(visibility);
//        etEmail.setVisibility(visibility);
//        etSenha.setVisibility(visibility);
//        etConfirmarSenha.setVisibility(visibility);
    }

    // Método para limpar os campos
    private void limparCampos() {
//        etNome.setText("");
//        etCpf.setText("");
//        etDataNascimento.setText("");
        etSexo.setText("");
        etPeso.setText("");
        etAltura.setText("");
        etCrm.setText("");
//        etTelefone.setText("");
        etInformacoesAdicionais.setText("");
//        etEmail.setText("");
//        etSenha.setText("");
//        etConfirmarSenha.setText("");
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
