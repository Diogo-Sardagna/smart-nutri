package Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "pacientes")
public class Paciente extends Pessoa {
//    @PrimaryKey(autoGenerate = true)
//    public int idPaciente;
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

    @Override
    public String toString() {
        return nome + "|"
                + cpf + "|"
                + dataNascimento + "|"
                + telefone + "|"
                + email + "|"
                + senha + "|"
                + peso + "|"
                + altura + "|"
                + sexo + "|"
                + informacoes;
    }
}

