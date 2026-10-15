package com.example.fittrack;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Mostra, para cada exercício do treino em execução, campos para registrar
 * séries, repetições e carga (RF06, RF07), e um checkbox para marcar o
 * exercício como concluído (RF08).
 */
public class RegistroExecucaoAdapter extends RecyclerView.Adapter<RegistroExecucaoAdapter.ViewHolder> {

    private List<RegistroExecucao> registros;

    public RegistroExecucaoAdapter(List<RegistroExecucao> registros) {
        this.registros = registros;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_registro_execucao, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RegistroExecucao registro = registros.get(position);

        holder.tvNomeExercicio.setText(registro.getExercicio().getNome());

        // Remove listeners antigos antes de preencher (evita bug de reciclagem do RecyclerView)
        holder.limparListeners();

        holder.etSeries.setText(registro.getSeries() == 0 ? "" : String.valueOf(registro.getSeries()));
        holder.etRepeticoes.setText(registro.getRepeticoes() == 0 ? "" : String.valueOf(registro.getRepeticoes()));
        holder.etCarga.setText(registro.getCarga() == 0 ? "" : String.valueOf(registro.getCarga()));
        holder.cbConcluido.setChecked(registro.isConcluido());

        holder.configurarListeners(registro);
    }

    @Override
    public int getItemCount() {
        return registros.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeExercicio;
        EditText etSeries, etRepeticoes, etCarga;
        CheckBox cbConcluido;

        private TextWatcher seriesWatcher, repeticoesWatcher, cargaWatcher;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomeExercicio = itemView.findViewById(R.id.tvNomeExercicio);
            etSeries = itemView.findViewById(R.id.etSeries);
            etRepeticoes = itemView.findViewById(R.id.etRepeticoes);
            etCarga = itemView.findViewById(R.id.etCarga);
            cbConcluido = itemView.findViewById(R.id.cbConcluido);
        }

        void limparListeners() {
            if (seriesWatcher != null) etSeries.removeTextChangedListener(seriesWatcher);
            if (repeticoesWatcher != null) etRepeticoes.removeTextChangedListener(repeticoesWatcher);
            if (cargaWatcher != null) etCarga.removeTextChangedListener(cargaWatcher);
            cbConcluido.setOnCheckedChangeListener(null);
        }

        void configurarListeners(RegistroExecucao registro) {
            seriesWatcher = criarWatcher(texto -> registro.setSeries(paraIntSeguro(texto)));
            etSeries.addTextChangedListener(seriesWatcher);

            repeticoesWatcher = criarWatcher(texto -> registro.setRepeticoes(paraIntSeguro(texto)));
            etRepeticoes.addTextChangedListener(repeticoesWatcher);

            cargaWatcher = criarWatcher(texto -> registro.setCarga(paraDoubleSeguro(texto)));
            etCarga.addTextChangedListener(cargaWatcher);

            cbConcluido.setOnCheckedChangeListener((buttonView, isChecked) -> registro.setConcluido(isChecked));
        }

        private int paraIntSeguro(String texto) {
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        private double paraDoubleSeguro(String texto) {
            try {
                return Double.parseDouble(texto);
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        private TextWatcher criarWatcher(TextoMudouCallback callback) {
            return new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                    callback.onTextoMudou(s.toString());
                }
            };
        }

        interface TextoMudouCallback {
            void onTextoMudou(String texto);
        }
    }
}
