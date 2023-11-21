//package Repository;
//
//import android.content.Context;
//import android.nfc.Tag;
//import android.util.Log;
//
//import androidx.room.Room;
//
//import java.util.List;
//
//import DAO.AppDataBase;
//import Model.Pessoa;
//
//public class PessoaRepository {
//
//    private Context context;
//    private AppDataBase appDataBase;
//
//    public PessoaRepository(Context context) {
//        this.context = context;
//        this.appDataBase = Room.databaseBuilder(context, AppDataBase.class, "com.example.smartnutri").allowMainThreadQueries().build();
//    }
//
//    public void insertPessoa(Pessoa pessoa) throws Exception {
//        try {
//            appDataBase.pessoaDao().insert(pessoa);
//        } catch (Exception e) {
//            Log.e("PessoaRepository", e.getMessage());
//            throw new Exception("Não foi possível registrar a pessoa. Tente novamente.");
//        }
//    }
//
//    public List<Pessoa> getAllPessoas() throws Exception {
//        try {
//            return appDataBase.pessoaDao().getAllPessoas();
//        } catch (Exception e) {
//            Log.e("PessoaRepository", e.getMessage());
//            throw new Exception("Não foi possível obter todas as pessoas. Tente novamente.");
//        }
//    }
//
//    public Pessoa getPessoaByName(String nome) throws Exception {
//        try {
//            return appDataBase.pessoaDao().getPessoaByName(nome);
//        } catch (Exception e) {
//            Log.e("PessoaRepository", e.getMessage());
//            throw new Exception("Não foi possível obter pessoa por nome. Tente novamente.");
//        }
//    }
//
//    public Pessoa getPessoaByEmail(String email) throws Exception {
//        try {
//            return appDataBase.pessoaDao().getPessoaByEmail(email);
//        } catch (Exception e) {
//            Log.e("PessoaRepository", e.getMessage());
//            throw new Exception("Não foi possível obter pessoa por e-mail. Tente novamente.");
//        }
//    }
//
//    public Pessoa getPessoaByCpf(String cpf) throws Exception {
//        try {
//            return appDataBase.pessoaDao().getPessoaByCpf(cpf);
//        } catch (Exception e) {
//            Log.e("PessoaRepository", e.getMessage());
//            throw new Exception("Não foi possível obter pessoa por CPF. Tente novamente.");
//        }
//    }
//}
