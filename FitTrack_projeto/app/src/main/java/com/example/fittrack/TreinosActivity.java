package com.example.fittrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TreinosActivity extends AppCompatActivity {

    EditText etNomeTreino;
    Button btSalvarTreino;
    View btTreinosSugeridos, btVoltar;
    RecyclerView rvExerciciosSelecionaveis, rvTreinos;

    ExercicioSelecionavelAdapter adapterSelecionaveis;
    TreinoAdapter adapterTreinos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treinos);

        btVoltar = findViewById(R.id.btVoltar);
        if (btVoltar != null) {
            btVoltar.setOnClickListener(v -> finish());
        }

        etNomeTreino = findViewById(R.id.etNomeTreino);
        btSalvarTreino = findViewById(R.id.btSalvarTreino);
        btTreinosSugeridos = findViewById(R.id.btTreinosSugeridos);
        rvExerciciosSelecionaveis = findViewById(R.id.rvExerciciosSelecionaveis);
        rvTreinos = findViewById(R.id.rvTreinos);

        if (btTreinosSugeridos != null) {
            btTreinosSugeridos.setOnClickListener(v -> {
                Intent intent = new Intent(TreinosActivity.this, TreinosSugeridosActivity.class);
                startActivity(intent);
            });
        }

        adapterSelecionaveis = new ExercicioSelecionavelAdapter(AppData.exercicios);
        rvExerciciosSelecionaveis.setLayoutManager(new LinearLayoutManager(this));
        rvExerciciosSelecionaveis.setAdapter(adapterSelecionaveis);

        adapterTreinos = new TreinoAdapter(AppData.treinos, new TreinoAdapter.OnTreinoClickListener() {
            @Override
            public void onTreinoClick(int posicao) {
                Intent intent = new Intent(TreinosActivity.this, ExecutarTreinoActivity.class);
                intent.putExtra(ExecutarTreinoActivity.EXTRA_TREINO_POSICAO, posicao);
                startActivity(intent);
            }

            @Override
            public void onTreinoLongClick(int posicao) {
                Treino t = AppData.treinos.get(posicao);
                new AlertDialog.Builder(TreinosActivity.this)
                        .setTitle("Excluir treino")
                        .setMessage("Deseja excluir o treino \"" + t.getNome() + "\"?")
                        .setPositiveButton("Excluir", (dialog, which) -> {
                            AppRepository.excluirTreino(TreinosActivity.this, t.getNome());
                            AppData.treinos.remove(posicao);
                            adapterTreinos.notifyItemRemoved(posicao);
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            }
        });
        rvTreinos.setLayoutManager(new LinearLayoutManager(this));
        rvTreinos.setAdapter(adapterTreinos);

        btSalvarTreino.setOnClickListener(v -> {
            String nome = etNomeTreino.getText().toString().trim();

            if (nome.isEmpty()) {
                Toast.makeText(this, "Digite um nome para o treino!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (adapterSelecionaveis.getSelecionados().isEmpty()) {
                Toast.makeText(this, "Selecione ao menos um exercício!", Toast.LENGTH_SHORT).show();
                return;
            }

            Treino treino = new Treino(nome);
            treino.getExercicios().addAll(adapterSelecionaveis.getSelecionados());

            AppData.treinos.add(treino);
            AppRepository.salvarTreino(this, treino);
            adapterTreinos.notifyItemInserted(AppData.treinos.size() - 1);

            etNomeTreino.setText("");
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapterTreinos != null) {
            adapterTreinos.notifyDataSetChanged();
        }
        if (adapterSelecionaveis != null) {
            adapterSelecionaveis.notifyDataSetChanged();
        }
    }
}
