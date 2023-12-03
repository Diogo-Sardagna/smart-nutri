package DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Model.Nutricionista;

@Dao
public interface NutricionistaDao {
    @Insert
    void insert(Nutricionista nutricionista);

    @Query("DELETE FROM nutricionistas")
    void excluirTodosOsRegistros();

    @Query("SELECT * FROM nutricionistas")
    List<Nutricionista> getAllNutricionistas();

    @Query("SELECT * FROM nutricionistas WHERE nome = :nome")
    Nutricionista getNutricionistaByName(String nome);

    @Query("SELECT * FROM nutricionistas WHERE email = :email")
    Nutricionista getNutricionistaByEmail(String email);

    @Query("SELECT * FROM nutricionistas WHERE cpf = :cpf")
    Nutricionista getNutricionistaByCpf(String cpf);
}
