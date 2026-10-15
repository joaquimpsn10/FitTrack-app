package com.example.fittrack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class TreinoSugeridoAdapter extends RecyclerView.Adapter<TreinoSugeridoAdapter.ViewHolder> {

    public interface OnAdicionarClickListener {
        void onAdicionar(TreinoSugerido treinoSugerido);
    }

    private List<TreinoSugerido> lista;
    private OnAdicionarClickListener listener;

    public TreinoSugeridoAdapter(List<TreinoSugerido> lista, OnAdicionarClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_treino_sugerido, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TreinoSugerido item = lista.get(position);
        holder.tvDiaSemana.setText(item.getDiaSemana());
        holder.tvTitulo.setText(item.getTitulo());
        holder.tvListaExercicios.setText(item.getExerciciosFormatados());

        holder.btAdicionar.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAdicionar(item);
                holder.btAdicionar.setText("✓ Adicionado");
                holder.btAdicionar.setEnabled(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDiaSemana, tvTitulo, tvListaExercicios;
        MaterialButton btAdicionar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDiaSemana = itemView.findViewById(R.id.tvDiaSemana);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvListaExercicios = itemView.findViewById(R.id.tvListaExercicios);
            btAdicionar = itemView.findViewById(R.id.btAdicionarRotina);
        }
    }
}
