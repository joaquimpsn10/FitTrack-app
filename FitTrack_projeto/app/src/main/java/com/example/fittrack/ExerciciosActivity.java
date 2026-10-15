package com.example.fittrack;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExerciciosActivity extends AppCompatActivity {

    EditText etNome, etGrupoMuscular;
    Button btAdicionar;
    View btVoltar;
    RecyclerView rvExercicios;

    ExercicioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios);

        btVoltar = findViewById(R.id.btVoltar);
        if (btVoltar != null) {
            btVoltar.setOnClickListener(v -> finish());
        }

        etNome = findViewById(R.id.etNome);
        etGrupoMuscular = findViewById(R.id.etGrupoMuscular);
        btAdicionar = findViewById(R.id.btAdicionar);
        rvExercicios = findViewById(R.id.rvExercicios);

        // RF02 - exclusão via toque longo em um item da lista
        adapter = new ExercicioAdapter(AppData.exercicios, posicao -> {
            Exercicio ex = AppData.exercicios.get(posicao);
            new AlertDialog.Builder(this)
                    .setTitle("Excluir exercício")
                    .setMessage("Deseja excluir \"" + ex.getNome() + "\"?")
                    .setPositiveButton("Excluir", (dialog, which) -> {
                        AppRepository.excluirExercicio(this, ex.getNome());
                        AppData.exercicios.remove(posicao);
                        adapter.notifyItemRemoved(posicao);
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });

        rvExercicios.setLayoutManager(new LinearLayoutManager(this));
        rvExercicios.setAdapter(adapter);

        // RF01 - cadastro de novos exercícios
        btAdicionar.setOnClickListener(v -> {
            String nome = etNome.getText().toString().trim();
            String grupo = etGrupoMuscular.getText().toString().trim();

            if (nome.isEmpty() || grupo.isEmpty()) {
                Toast.makeText(this, "Preencha os dois campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            Exercicio novoExercicio = new Exercicio(nome, grupo);
            AppData.exercicios.add(novoExercicio);
            AppRepository.salvarExercicio(this, novoExercicio);
            adapter.notifyItemInserted(AppData.exercicios.size() - 1);

            etNome.setText("");
            etGrupoMuscular.setText("");
        });
    }
}
