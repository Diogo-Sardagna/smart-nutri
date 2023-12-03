package Repository;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import java.util.List;

import DAO.AppDataBase;
import Model.Agendamento;
import Model.Nutricionista;
import Model.Paciente;

public interface AgendamentoRepository {
    void insert(Agendamento agendamento);

    List<Agendamento> getAllAgendamentos();

    List<Agendamento> getAgendamentoByNutricionista(Nutricionista nutricionista);

    Agendamento getAgendamentoByPaciente(Paciente paciente);

    List<Agendamento> getAgendamento(Boolean aprovado);

    void aprovarAgendamento(int agendamentoId);
}

//public class AgendamentoRepository {
//
//    private Context context;
//    private AppDataBase appDataBase;
//
//    public AgendamentoRepository(Context context) {
//        this.context = context;
//        this.appDataBase = Room.databaseBuilder(context, AppDataBase.class, "com.example.smartnutri").allowMainThreadQueries().build();
//    }
//
//    public void insert(Agendamento agendamento) {
//        try {
//            appDataBase.agendamentoDao().insert(agendamento);
//        } catch (Exception e) {
//            Log.e("AgendamentoRepository", e.getMessage());
//        }
//    }
//
//    public List<Agendamento> getAllAgendamentos() {
//        try {
//            return appDataBase.agendamentoDao().getAllAgendamentos();
//        } catch (Exception e) {
//            Log.e("AgendamentoRepository", e.getMessage());
//            return null;
//        }
//    }
//
//    public List<Agendamento> getAgendamentoByNutricionista(Nutricionista nutricionista) {
//        try {
//            return appDataBase.agendamentoDao().getAgendamentoByNutricionista(nutricionista);
//        } catch (Exception e) {
//            Log.e("AgendamentoRepository", e.getMessage());
//            return null;
//        }
//    }
//
//    public Agendamento getAgendamentoByPaciente(Paciente paciente) {
//        try {
//            return appDataBase.agendamentoDao().getAgendamentoByPaciente(paciente);
//        } catch (Exception e) {
//            Log.e("AgendamentoRepository", e.getMessage());
//            return null;
//        }
//    }
//
//    public List<Agendamento> getAgendamentos(Boolean aprovado) {
//        try {
//            return appDataBase.agendamentoDao().getAgendamentos(aprovado);
//        } catch (Exception e) {
//            Log.e("AgendamentoRepository", e.getMessage());
//            return null;
//        }
//    }
//
//    public void aprovarAgendamento(int agendamentoId) {
//        try {
//            appDataBase.agendamentoDao().aprovarAgendamento(agendamentoId);
//        } catch (Exception e) {
//            Log.e("AgendamentoRepository", e.getMessage());
//        }
//    }
//}