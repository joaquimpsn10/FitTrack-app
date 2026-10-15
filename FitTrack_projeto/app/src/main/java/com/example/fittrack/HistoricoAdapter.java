package com.example.fittrack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.ViewHolder> {

    private List<Treino> treinosConcluidos;
    private final SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());

    public HistoricoAdapter(List<Treino> treinosConcluidos) {
        this.treinosConcluidos = treinosConcluidos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_historico_treino, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Treino treino = treinosConcluidos.get(position);
        holder.tvNomeTreino.setText(treino.getNome());
        holder.tvData.setText(formato.format(treino.getDataExecucao()));

        // RF10 - acompanhamento da evolução das cargas: mostra a carga usada em cada exercício
        StringBuilder detalhes = new StringBuilder();
        for (RegistroExecucao r : treino.getRegistros()) {
            detalhes.append("• ").append(r.getExercicio().getNome())
                    .append(": ").append(r.getSeries()).append("x").append(r.getRepeticoes())
                    .append(" — ").append(r.getCarga()).append("kg\n");
        }
        holder.tvDetalhes.setText(detalhes.toString().trim());
    }

    @Override
    public int getItemCount() {
        return treinosConcluidos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeTreino, tvData, tvDetalhes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomeTreino = itemView.findViewById(R.id.tvNomeTreino);
            tvData = itemView.findViewById(R.id.tvData);
            tvDetalhes = itemView.findViewById(R.id.tvDetalhes);
        }
    }
}
