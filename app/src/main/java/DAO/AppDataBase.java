package DAO;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import Model.Pessoa;
import Model.Paciente;
import Model.Nutricionista;

@Database(entities = {/*Pessoa.class,*/ Paciente.class, Nutricionista.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

//    public abstract PessoaDao pessoaDao();

    public abstract PacienteDao pacienteDao();

    public abstract NutricionistaDao nutricionistaDao();
}