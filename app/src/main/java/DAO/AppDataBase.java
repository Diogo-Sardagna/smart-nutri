package DAO;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import Model.Converters;
import Model.Pessoa;
import Model.Paciente;
import Model.Nutricionista;
import Model.Agendamento;
import Model.PlanoAlimentar;

//@TypeConverters({Converters.class})
@Database(entities = {Paciente.class, Nutricionista.class/*, Agendamento.class , PlanoAlimentar.class*/}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

//    private static volatile AppDataBase INSTANCE;

//    public abstract PessoaDao pessoaDao();

    public abstract PacienteDao pacienteDao();

    public abstract NutricionistaDao nutricionistaDao();

//    public abstract AgendamentoDao agendamentoDao();
//
//    public abstract PlanoAlimentarDao planoAlimentarDao();

//    public static final Migration MIGRATION_1_3 = new Migration(1, 3) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            // Adicione código de migração aqui se necessário
//        }
//    };

//    public static AppDataBase getInstance(Context context) {
//        if (INSTANCE == null) {
//            synchronized (AppDataBase.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = buildDatabase(context);
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//    private static AppDataBase buildDatabase(final Context context) {
//        return Room.databaseBuilder(
//                        context.getApplicationContext(),
//                        AppDataBase.class,
//                        "smartnutri"
//                )
//                .addMigrations(MIGRATION_1_3)
//                .build();
//    }
}