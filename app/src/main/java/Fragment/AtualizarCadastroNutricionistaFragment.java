package Fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartnutri.R;

public class AtualizarCadastroNutricionistaFragment extends Fragment {

    public AtualizarCadastroNutricionistaFragment(FragmentManager supportFragmentManager) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_atualizar_cadastro_nutricionista, container, false);
    }
}