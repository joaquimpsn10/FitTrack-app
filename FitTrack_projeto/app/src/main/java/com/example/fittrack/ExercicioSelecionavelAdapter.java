package com.example.fittrack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExercicioSelecionavelAdapter extends RecyclerView.Adapter<ExercicioSelecionavelAdapter.ViewHolder> {

    private List<Exercicio> listaExercicios;
    private List<Exercicio> selecionados = new ArrayList<>();

    public ExercicioSelecionavelAdapter(List<Exercicio> listaExercicios) {
        this.listaExercicios = listaExercicios;
    }

    public List<Exercicio> getSelecionados() {
        return selecionados;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exercicio_selecionavel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exercicio exercicio = listaExercicios.get(position);
        holder.tvNome.setText(exercicio.getNome());
        holder.tvGrupo.setText(exercicio.getGrupoMuscular());

        // Evita que o checkbox "lembre" do estado errado quando a view é reciclada
        holder.cbSelecionado.setOnCheckedChangeListener(null);
        holder.cbSelecionado.setChecked(selecionados.contains(exercicio));

        holder.cbSelecionado.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selecionados.add(exercicio);
            } else {
                selecionados.remove(exercicio);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaExercicios.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome, tvGrupo;
        CheckBox cbSelecionado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvGrupo = itemView.findViewById(R.id.tvGrupo);
            cbSelecionado = itemView.findViewById(R.id.cbSelecionado);
        }
    }
}
