package VisualComponent;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import Model.Agendamento;

public class AgendamentoAdapter extends BaseAdapter {

    private Context context;
    private List<Agendamento> agendamentos;

    public AgendamentoAdapter(Context context, List<Agendamento> agendamentos){
        this.context = context;
        this.agendamentos = agendamentos;
    }

    @Override
    public int getCount() {
        return agendamentos.size();
    }

    @Override
    public Object getItem(int position) {
        return agendamentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return agendamentos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView texto = new TextView(context);
        Agendamento agendamento = agendamentos.get(position);
        texto.setText(agendamento.getPaciente().getNome());
        return texto;
    }
}
