package DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Model.Agendamento;
import Model.Nutricionista;
import Model.Paciente;

@Dao
public interface AgendamentoDao {
    @Insert
    void insert(Agendamento agendamento);

    @Query("SELECT * FROM agendamento")
    List<Agendamento> getAllAgendamentos();

    @Query("SELECT * FROM agendamento WHERE nutricionista = :nutricionista")
    List<Agendamento> getAgendamentoByNutricionista(Nutricionista nutricionista);

    @Query("SELECT * FROM agendamento WHERE paciente = :paciente")
    Agendamento getAgendamentoByPaciente(Paciente paciente);

    @Query("SELECT * FROM agendamento WHERE aprovado = :aprovado")
    List<Agendamento> getAgendamento(Boolean aprovado);
}
