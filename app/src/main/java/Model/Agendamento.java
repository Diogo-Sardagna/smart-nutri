package Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "agendamento")
public class Agendamento {
    @NotNull
    private Paciente paciente;
    @NotNull
    private Nutricionista nutricionista;
    @NotNull
    private Calendar data;
    private Boolean aprovado;

    public Agendamento(Nutricionista nutricionista, Calendar data, Paciente paciente) {
        this.nutricionista = nutricionista;
        this.data = data;
        this.paciente = paciente;
    }

    public Agendamento(Nutricionista nutricionista, Calendar data, Paciente paciente, Boolean aprovado) {
        this(nutricionista, data, paciente);
        this.aprovado = aprovado;
    }

    @NotNull
    public Nutricionista getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(@NotNull Nutricionista nutricionista) {
        this.nutricionista = nutricionista;
    }

    @NotNull
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(@NotNull Paciente paciente) {
        this.paciente = paciente;
    }

    public Boolean getAprovado() {
        return aprovado;
    }

    public void setAprovado(Boolean aprovado) {
        this.aprovado = aprovado;
    }

    @NotNull
    public Calendar getData() {
        return data;
    }

    public void setData(@NotNull Calendar data) {
        this.data = data;
    }

    @Ignore
    public String getDataFormatada() {
        // Formatando a data para exibição
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(data.getTime());
    }
}
