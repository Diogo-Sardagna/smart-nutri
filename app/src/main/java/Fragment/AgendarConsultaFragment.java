package Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.smartnutri.R;

import java.util.Calendar;

import Model.Agendamento;
import Model.Nutricionista;

public class AgendarConsultaFragment extends AppCompatActivity {

    private TextView dataSelecionadaTextView;
    private Button selecionarDataButton;
    private Spinner nutricionistaSpinner;

    private Calendar calendar;
    private Agendamento agendamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_agendar_consulta);

        dataSelecionadaTextView = findViewById(R.id.dataSelecionadaTextView);
        selecionarDataButton = findViewById(R.id.selecionarDataButton);
        nutricionistaSpinner = findViewById(R.id.nutricionistaSpinner);

        // Criar instâncias de nutricionistas para exibir na lista
        Nutricionista nutricionista1 = new Nutricionista("A","123","09/05/2002","987654","d@gmail.com","Dgs2002@","1111");
        Nutricionista nutricionista2 = new Nutricionista("Dr. Marcos","123","09/05/2002","987654","di@gmail.com","Dgs2002@","2222");

        // Configurar o Spinner com a lista de nutricionistas
        ArrayAdapter<Nutricionista> nutricionistaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        nutricionistaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nutricionistaAdapter.add(nutricionista1);
        nutricionistaAdapter.add(nutricionista2);
        nutricionistaSpinner.setAdapter(nutricionistaAdapter);

        calendar = Calendar.getInstance();
//        agendamento = new Agendamento((Nutricionista) nutricionistaSpinner.getSelectedItem(), calendar);

        dataSelecionadaTextView.setText(agendamento.getDataFormatada());

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

//                agendamento = new Agendamento((Nutricionista) nutricionistaSpinner.getSelectedItem(), calendar);

                dataSelecionadaTextView.setText(agendamento.getDataFormatada());
            }
        };

        selecionarDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(
                        AgendarConsultaFragment.this,
                        dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });
    }
}