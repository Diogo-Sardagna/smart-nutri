package Repository;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import androidx.room.Room;

import java.util.List;

import DAO.AppDataBase;
import Model.Paciente;

public class PacienteRepository {

    private Context context;
    private AppDataBase appDataBase;

    public PacienteRepository(Context context) {
        this.context = context;
        this.appDataBase = Room.databaseBuilder(context, AppDataBase.class, "com.example.smartnutri").allowMainThreadQueries().build();
    }

    public void insertPaciente(Paciente paciente) throws Exception {
        try {
            appDataBase.pacienteDao().insert(paciente);
        } catch (Exception e) {
            Log.e("PacienteRepository", e.getMessage());
            throw new Exception("Não foi possível registrar o paciente. Tente novamente.");
        }
    }

    public List<Paciente> getAllPacientes() throws Exception {
        try {
            return appDataBase.pacienteDao().getAllPacientes();
        } catch (Exception e) {
            Log.e("PacienteRepository", e.getMessage());
            throw new Exception("Não foi possível obter todos os pacientes. Tente novamente.");
        }
    }

    public Paciente getPacienteByName(String nome) throws Exception {
        try {
            return appDataBase.pacienteDao().getPacienteByName(nome);
        } catch (Exception e) {
            Log.e("PacienteRepository", e.getMessage());
            throw new Exception("Não foi possível obter paciente por nome. Tente novamente.");
        }
    }

    public Paciente getPacienteByEmail(String email) throws Exception {
        try {
            return appDataBase.pacienteDao().getPacienteByEmail(email);
        } catch (Exception e) {
            Log.e("PacienteRepository", e.getMessage());
            throw new Exception("Não foi possível obter paciente por e-mail. Tente novamente.");
        }
    }

    public Paciente getPacienteByCpf(String cpf) throws Exception {
        try {
            return appDataBase.pacienteDao().getPacienteByCpf(cpf);
        } catch (Exception e) {
            Log.e("PacienteRepository", e.getMessage());
            throw new Exception("Não foi possível obter paciente por CPF. Tente novamente.");
        }
    }

}

