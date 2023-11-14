package DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Model.Paciente;

@Dao
public interface PacienteDao {
    @Insert
    void insert(Paciente paciente);

    @Query("SELECT * FROM pacientes")
    List<Paciente> getAllPacientes();

    @Query("SELECT * FROM pacientes WHERE nome = :nome")
    Paciente getPacienteByName(String nome);

    @Query("SELECT * FROM pacientes WHERE email = :email")
    Paciente getPacienteByEmail(String email);

    @Query("SELECT * FROM pacientes WHERE cpf = :cpf")
    Paciente getPacienteByCpf(String cpf);
}
