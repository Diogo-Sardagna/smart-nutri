package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.PlanoAlimentar;

public class PlanoAlimentarSingleton {
    private static PlanoAlimentarSingleton instance;
    private List<PlanoAlimentar> planosAlimentares;

    private PlanoAlimentarSingleton() {
        planosAlimentares = new ArrayList<>();
    }

    public static synchronized PlanoAlimentarSingleton getInstance() {
        if (instance == null) {
            instance = new PlanoAlimentarSingleton();
        }
        return instance;
    }

    public void adicionarAgendamento(PlanoAlimentar planoAlimentar) {
        planosAlimentares.add(planoAlimentar);
    }

    public List<PlanoAlimentar> getPlanosAlimentares() {
        return planosAlimentares;
    }
}
