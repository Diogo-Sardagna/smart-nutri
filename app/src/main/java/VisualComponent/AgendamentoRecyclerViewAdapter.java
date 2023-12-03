package VisualComponent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartnutri.R;

import java.util.List;

import Model.Agendamento;
import Observador.ConsultaControllerObserver;

public class AgendamentoRecyclerViewAdapter extends RecyclerView.Adapter<AgendamentoRecyclerViewAdapter.ViewHolder> {

    private List<Agendamento> agendamentos;
    private ConsultaControllerObserver consultaControllerObserver;

    public AgendamentoRecyclerViewAdapter(List<Agendamento> agendamentos, ConsultaControllerObserver consultaControllerObserver) {
        this.agendamentos = agendamentos;
        this.consultaControllerObserver = consultaControllerObserver;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idAgendamento;
        TextView nomePaciente;
        TextView dataAgendada;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.idAgendamento = itemView.findViewById(R.id.idAgendamento);
            this.nomePaciente = itemView.findViewById(R.id.nomePaciente);
            this.dataAgendada = itemView.findViewById(R.id.dataAgendada);
        }
    }

    @NonNull
    @Override
    public AgendamentoRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.agendamento_recyclerview_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AgendamentoRecyclerViewAdapter.ViewHolder holder, int position) {
        Agendamento agendamento = agendamentos.get(position);

        holder.idAgendamento.setText("#" + (int) agendamento.getId());
        holder.nomePaciente.setText(agendamento.getPaciente().getNome());
        holder.dataAgendada.setText(agendamento.getDataFormatada());

        Context context = holder.itemView.getContext();
        holder.itemView.setOnClickListener(v -> {
            this.consultaControllerObserver.selecionaAgendamento(agendamento);
        });
    }

    @Override
    public int getItemCount() {
        return agendamentos.size();
    }
}
