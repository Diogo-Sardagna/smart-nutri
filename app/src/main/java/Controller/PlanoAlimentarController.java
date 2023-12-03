package Controller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import Model.PlanoAlimentar;

public class PlanoAlimentarController {

    private PlanoAlimentarSingleton planoAlimentarSingleton = PlanoAlimentarSingleton.getInstance();

    private Context context;

    private View view;

    public PlanoAlimentarController(Context context) {
        this.context = context;
    }

    public void insert(PlanoAlimentar planoAlimentar) {
        planoAlimentarSingleton.adicionarAgendamento(planoAlimentar);
    }

    public List<PlanoAlimentar> getAllPlanosAlimentares() {
        return planoAlimentarSingleton.getPlanosAlimentares();
    }

    public void salvarPlanoAlimentar(PlanoAlimentar planoAlimentar) throws Exception {
        try {
            List<PlanoAlimentar> planosSalvos = getAllPlanosAlimentares();

//            if (planosSalvos.size() == 0 || planosSalvos == null) {
//                throw new Exception("lista vazia.");
//            }

            boolean planoExistente = planosSalvos.stream()
                    .anyMatch(plano -> plano.getAgendamento().getId() == planoAlimentar.getAgendamento().getId());

//            final Boolean[] planoExistente = {};
//            planoExistente[0] = false;
//            planosSalvos.forEach(plano -> {
//                if (plano.getAgendamento().getId() == planoAlimentar.getAgendamento().getId()) {
//                    planoExistente[0] = true;
//                }
//            });

            if (planoExistente) {
                showMessage("Já existe um plano alimentar para o mesmo agendamento. Não é permitido duplicar.");
                throw new Exception("Já existe um plano alimentar para o mesmo agendamento. Não é permitido duplicar.");
            }

            insert(planoAlimentar);
        } catch (Exception e) {
            Log.e("PlanoAlimentarController", e.getMessage());
            throw new Exception("Não foi possível registrar o plano alimentar. Tente novamente.", e);
        }
    }

    public void showMessage(String msg) {
        Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show();
    }

}
