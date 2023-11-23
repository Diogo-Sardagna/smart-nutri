package Fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartnutri.R;

public class AprovarConsultaFragment extends Fragment {

    public AprovarConsultaFragment(FragmentManager supportFragmentManager) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aprovar_consulta, container, false);
    }
}