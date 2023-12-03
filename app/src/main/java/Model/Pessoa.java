package Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "pessoas")
public abstract class Pessoa {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    public int id;

    @NotNull
    protected String nome;
    @NotNull
    protected String cpf;
    @NotNull
    protected String dataNascimento;
    @NotNull
    protected String telefone;
    @NotNull
    protected String email;
    @NotNull
    protected String senha;

    public Pessoa() {}

    public Pessoa(String nome, String cpf, String dataNascimento, String telefone, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    @NotNull
    public String getNome() {
        return nome;
    }

    public void setNome(@NotNull String nome) {
        this.nome = nome;
    }

    @NotNull
    public String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull String cpf) {
        this.cpf = cpf;
    }

    @NotNull
    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(@NotNull String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @NotNull
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotNull String telefone) {
        this.telefone = telefone;
    }

    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    @NotNull
    public String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull String senha) {
        this.senha = senha;
    }
}

