package Model;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "plano_alimentar")
public class PlanoAlimentar {
    private static int geradorCodigo = 0;
    private int id;
//    @PrimaryKey(autoGenerate = true)
//    private int id;

    @ColumnInfo(name = "agendamento")
    private Agendamento agendamento;

    @ColumnInfo(name = "plano")
    private String plano;

    public PlanoAlimentar(Agendamento agendamento, String plano) {
        this.id = geradorCodigo++;
        this.agendamento = agendamento;
        this.plano = plano;
    }

    // Getters and setters for PlanoAlimentar
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(@NotNull Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    @NotNull
    public String getPlano() {
        return plano;
    }

    public void setPlano(@NotNull String plano) {
        this.plano = plano;
    }

    // Outros métodos de retorno
    @Ignore
    public Paciente getPaciente() {
        return agendamento.getPaciente();
    }

    @Ignore
    public Nutricionista getNutricionista() {
        return agendamento.getNutricionista();
    }

    @Ignore
    public String getDataFormatada() {
        return agendamento.getDataFormatada();
    }

    @Ignore
    public Boolean getAprovado() {
        return agendamento.getAprovado();
    }
}
