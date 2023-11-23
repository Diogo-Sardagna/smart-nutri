package Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "pacientes")
public class Paciente extends Pessoa {
    @NotNull
    private Double peso;
    @NotNull
    private Double altura;
    @NotNull
    private String sexo;
    @NotNull
    private String informacoes;

    public Paciente() {}

    public Paciente(String nome, String cpf, String dataNascimento, String telefone, String email, String senha,
                    Double peso, Double altura, String sexo, String informacoes) {
        super(nome, cpf, dataNascimento, telefone, email, senha);
        this.peso = peso;
        this.altura = altura;
        this.sexo = sexo;
        this.informacoes = informacoes;
    }

    @NotNull
    public Double getPeso() {
        return peso;
    }

    public void setPeso(@NotNull Double peso) {
        this.peso = peso;
    }

    @NotNull
    public Double getAltura() {
        return altura;
    }

    public void setAltura(@NotNull Double altura) {
        this.altura = altura;
    }

    @NotNull
    public String getSexo() {
        return sexo;
    }

    public void setSexo(@NotNull String sexo) {
        this.sexo = sexo;
    }

    @NotNull
    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(@NotNull String informacoes) {
        this.informacoes = informacoes;
    }

    public Paciente convertStringToPaciente(String pacienteString) {
        // Use o mesmo caractere de delimitação utilizado no método convertToString
        char delimiter = '|';
        String[] campos = pacienteString.split(String.valueOf(delimiter));

//        return nome + delimiter + cpf + delimiter + dataNascimento + delimiter +
//                telefone + delimiter + email + delimiter + senha + delimiter +
//                peso + delimiter + altura + delimiter + sexo + delimiter + informacoes;

        // Certifique-se de validar o tamanho do array para evitar IndexOutOfBoundsException
        if (campos.length == 10) {
            return new Paciente(
                    campos[0], campos[1], campos[2], campos[3], campos[4],
                    campos[5], Double.parseDouble(campos[6]), Double.parseDouble(campos[7]),
                    campos[8], campos[9]
            );
        } else {
            // Manipule o caso em que a string não possui o formato esperado
            return null;
        }
    }

}

