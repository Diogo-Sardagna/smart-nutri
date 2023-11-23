package DAO;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import Model.Pessoa;
import Model.Paciente;
import Model.Nutricionista;
import Model.Agendamento;

@Database(entities = {Paciente.class, Nutricionista.class, Agendamento.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

//    public abstract PessoaDao pessoaDao();

    public abstract PacienteDao pacienteDao();

    public abstract NutricionistaDao nutricionistaDao();

    public abstract AgendamentoDao agendamentoDao();
}