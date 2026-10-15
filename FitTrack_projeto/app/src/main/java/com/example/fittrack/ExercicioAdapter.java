package com.example.fittrack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExercicioAdapter extends RecyclerView.Adapter<ExercicioAdapter.ExercicioViewHolder> {

    public interface OnExercicioLongClickListener {
        void onExercicioLongClick(int posicao);
    }

    private List<Exercicio> listaExercicios;
    private OnExercicioLongClickListener listener;

    public ExercicioAdapter(List<Exercicio> listaExercicios, OnExercicioLongClickListener listener) {
        this.listaExercicios = listaExercicios;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ExercicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercicio, parent, false);
        return new ExercicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercicioViewHolder holder, int position) {
        Exercicio exercicio = listaExercicios.get(position);
        holder.tvNome.setText(exercicio.getNome());
        holder.tvGrupo.setText(exercicio.getGrupoMuscular());

        // RF02 - editar/excluir: aqui implementamos a exclusão via toque longo
        holder.itemView.setOnLongClickListener(v -> {
            listener.onExercicioLongClick(holder.getAdapterPosition());
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listaExercicios.size();
    }

    public static class ExercicioViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome, tvGrupo;

        public ExercicioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvGrupo = itemView.findViewById(R.id.tvGrupo);
        }
    }
}
