package DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Model.Agendamento;
import Model.PlanoAlimentar;

@Dao
public interface PlanoAlimentarDao {
    @Insert
    void inserirPlanoAlimentar(PlanoAlimentar planoAlimentar);

    @Query("SELECT * FROM plano_alimentar")
    List<PlanoAlimentar> obterTodosOsPlanos();

//    @Query("SELECT * FROM plano_alimentar WHERE id = :agendamentoId")
//    PlanoAlimentar obterPlanoPorAgendamentoId(long agendamentoId);

    @Query("SELECT * FROM plano_alimentar WHERE agendamento = :agendamento")
    PlanoAlimentar obterPlanoPorAgendamento(Agendamento agendamento);

    // Adicione outros métodos de consulta ou atualização conforme necessário
}
