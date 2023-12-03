package Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "nutricionistas")
public class Nutricionista extends Pessoa {
//    @PrimaryKey(autoGenerate = true)
//    public int idNutricionista;

    @NotNull
    private String crm;

    public Nutricionista() {}

    public Nutricionista(String nome, String cpf, String dataNascimento, String telefone, String email, String senha, String crm) {
        super(nome, cpf, dataNascimento, telefone, email, senha);
        this.crm = crm;
    }

    @NotNull
    public String getCrm() {
        return crm;
    }

    public void setCrm(@NotNull String crm) {
        this.crm = crm;
    }

    @Override
    public String toString() {
        return nome + "|"
                + cpf + "|"
                + dataNascimento + "|"
                + telefone + "|"
                + email + "|"
                + senha + "|"
                + crm;
    }
}

