package com.example.fittrack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TreinoAdapter extends RecyclerView.Adapter<TreinoAdapter.ViewHolder> {

    public interface OnTreinoClickListener {
        void onTreinoClick(int posicao);
        void onTreinoLongClick(int posicao);
    }

    private List<Treino> listaTreinos;
    private OnTreinoClickListener listener;

    public TreinoAdapter(List<Treino> listaTreinos, OnTreinoClickListener listener) {
        this.listaTreinos = listaTreinos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_treino, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Treino treino = listaTreinos.get(position);
        holder.tvNomeTreino.setText(treino.getNome());

        int qtd = treino.getExercicios().size();
        String texto = qtd + (qtd == 1 ? " exercício" : " exercícios");
        if (treino.isConcluido()) {
            texto += " • Concluído";
        }
        holder.tvQtdExercicios.setText(texto);

        // Toque simples = abrir/executar o treino (RF06 a RF08)
        holder.itemView.setOnClickListener(v -> listener.onTreinoClick(holder.getAdapterPosition()));

        // Toque longo = excluir o treino (RF04)
        holder.itemView.setOnLongClickListener(v -> {
            listener.onTreinoLongClick(holder.getAdapterPosition());
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listaTreinos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeTreino, tvQtdExercicios;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomeTreino = itemView.findViewById(R.id.tvNomeTreino);
            tvQtdExercicios = itemView.findViewById(R.id.tvQtdExercicios);
        }
    }
}
