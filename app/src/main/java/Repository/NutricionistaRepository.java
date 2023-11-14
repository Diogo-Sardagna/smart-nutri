package Repository;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import androidx.room.Room;

import java.util.List;

import DAO.AppDataBase;
import Model.Nutricionista;

public class NutricionistaRepository {

    private Context context;
    private AppDataBase appDataBase;

    public NutricionistaRepository(Context context) {
        this.context = context;
        this.appDataBase = Room.databaseBuilder(context, AppDataBase.class, "com.example.smartnutri").allowMainThreadQueries().build();
    }

    public void insertNutricionista(Nutricionista nutricionista) throws Exception {
        try {
            appDataBase.nutricionistaDao().insert(nutricionista);
        } catch (Exception e) {
            Log.e("NutricionistaRepository", e.getMessage());
            throw new Exception("Não foi possível registrar o nutricionista. Tente novamente.");
        }
    }

    public List<Nutricionista> getAllNutricionistas() throws Exception {
        try {
            return appDataBase.nutricionistaDao().getAllNutricionistas();
        } catch (Exception e) {
            Log.e("NutricionistaRepository", e.getMessage());
            throw new Exception("Não foi possível obter todos os nutricionistas. Tente novamente.");
        }
    }

    public Nutricionista getNutricionistaByName(String nome) throws Exception {
        try {
            return appDataBase.nutricionistaDao().getNutricionistaByName(nome);
        } catch (Exception e) {
            Log.e("NutricionistaRepository", e.getMessage());
            throw new Exception("Não foi possível obter nutricionista por nome. Tente novamente.");
        }
    }

    public Nutricionista getNutricionistaByEmail(String email) throws Exception {
        try {
            return appDataBase.nutricionistaDao().getNutricionistaByEmail(email);
        } catch (Exception e) {
            Log.e("NutricionistaRepository", e.getMessage());
            throw new Exception("Não foi possível obter nutricionista por e-mail. Tente novamente.");
        }
    }

    public Nutricionista getNutricionistaByCpf(String cpf) throws Exception {
        try {
            return appDataBase.nutricionistaDao().getNutricionistaByCpf(cpf);
        } catch (Exception e) {
            Log.e("NutricionistaRepository", e.getMessage());
            throw new Exception("Não foi possível obter nutricionista por CPF. Tente novamente.");
        }
    }
}
