package DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Model.Pessoa;

@Dao
public interface PessoaDao<T extends Pessoa> {
    @Insert
    void insert(T pessoa);

    @Query("SELECT * FROM pessoas")
    List<T> getAllPessoas();

    @Query("SELECT * FROM pessoas WHERE nome = :nome")
    T getPessoaByName(String nome);

    @Query("SELECT * FROM pessoas WHERE email = :email")
    T getPessoaByEmail(String email);

    @Query("SELECT * FROM pessoas WHERE cpf = :cpf")
    T getPessoaByCpf(String cpf);
}

