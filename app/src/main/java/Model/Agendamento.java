package Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

//    @Embedded
@Entity(tableName = "agendamento")
public class Agendamento {
    private static int geradorCodigo = 0;
    private int id;

//    @PrimaryKey(autoGenerate = true)
//    public int id;
    @ColumnInfo(name = "paciente")
    private Paciente paciente;
    @ColumnInfo(name = "nutricionista")
    private Nutricionista nutricionista;
    @ColumnInfo(name = "data")
    private Calendar data;
    @NotNull
    private Boolean aprovado;

    public Agendamento(Nutricionista nutricionista, Calendar data, Paciente paciente, Boolean aprovado) {
        this.id = geradorCodigo++;
        this.nutricionista = nutricionista;
        this.data = data;
        this.paciente = paciente;
        this.aprovado = aprovado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @NotNull
    public Boolean getAprovado() {
        return aprovado;
    }

    public void setAprovado(@NotNull Boolean aprovado) {
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
