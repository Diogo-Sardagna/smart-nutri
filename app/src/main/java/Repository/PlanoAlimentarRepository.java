package Repository;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import java.util.List;

import DAO.AppDataBase;
import Model.Agendamento;
import Model.PlanoAlimentar;

public interface PlanoAlimentarRepository {
    void insert(PlanoAlimentar planoAlimentar);

    List<PlanoAlimentar> getAllplanoAlimentar();
}

/*public class PlanoAlimentarRepository {

    private Context context;
    private AppDataBase appDataBase;

    public PlanoAlimentarRepository(Context context) {
        this.context = context;
        this.appDataBase = Room.databaseBuilder(context, AppDataBase.class, "com.example.smartnutri").allowMainThreadQueries().build();
    }

    public void salvarPlanoAlimentar(PlanoAlimentar planoAlimentar) throws Exception {
        try {
            List<PlanoAlimentar> planosSalvos = obterTodosOsPlanos();

            boolean planoExistente = planosSalvos.stream()
                    .anyMatch(plano -> plano.getAgendamento().getId() == planoAlimentar.getAgendamento().getId());

//            boolean planoExistente = false;
//            planosSalvos.forEach(plano -> {
//                if (plano.getAgendamento().getId() == novoPlanoAlimentar.getAgendamento().getId()) {
//                    planoExistente = true;
//                }
//            });

            if (planoExistente) {
                throw new Exception("Já existe um plano alimentar para o mesmo agendamento. Não é permitido duplicar.");
            }

            appDataBase.planoAlimentarDao().inserirPlanoAlimentar(planoAlimentar);
        } catch (Exception e) {
            Log.e("PlanoAlimentarRepository", e.getMessage());
            throw new Exception("Não foi possível registrar o plano alimentar. Tente novamente.");
        }
    }

    public void inserirPlanoAlimentar(PlanoAlimentar planoAlimentar) throws Exception {
        try {
            appDataBase.planoAlimentarDao().inserirPlanoAlimentar(planoAlimentar);
        } catch (Exception e) {
            Log.e("PlanoAlimentarRepository", e.getMessage());
            throw new Exception("Não foi possível registrar o plano alimentar. Tente novamente.");
        }
    }

    public List<PlanoAlimentar> obterTodosOsPlanos() throws Exception {
        try {
            return appDataBase.planoAlimentarDao().obterTodosOsPlanos();
        } catch (Exception e) {
            Log.e("PlanoAlimentarRepository", e.getMessage());
            throw new Exception("Não foi possível obter todos os planos alimentares. Tente novamente.");
        }
    }

//    public PlanoAlimentar obterPlanoPorAgendamentoId(int agendamentoId) throws Exception {
//        try {
//            return appDataBase.planoAlimentarDao().obterPlanoPorAgendamentoId(agendamentoId);
//        } catch (Exception e) {
//            Log.e("PlanoAlimentarRepository", e.getMessage());
//            throw new Exception("Não foi possível obter o plano alimentar por agendamento ID. Tente novamente.");
//        }
//    }

    public PlanoAlimentar obterPlanoPorAgendamento(Agendamento agendamento) throws Exception {
        try {
            return appDataBase.planoAlimentarDao().obterPlanoPorAgendamento(agendamento);
        } catch (Exception e) {
            Log.e("PlanoAlimentarRepository", e.getMessage());
            throw new Exception("Não foi possível obter o plano alimentar pelo agendamento. Tente novamente.");
        }
    }

}*/

