package com.example.fittrack;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExecutarTreinoActivity extends AppCompatActivity {

    public static final String EXTRA_TREINO_POSICAO = "treino_posicao";

    TextView tvNomeTreino;
    RecyclerView rvRegistros;
    Button btFinalizarTreino;
    View btVoltar;

    Treino treino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executar_treino);

        btVoltar = findViewById(R.id.btVoltar);
        if (btVoltar != null) {
            btVoltar.setOnClickListener(v -> finish());
        }

        int posicao = getIntent().getIntExtra(EXTRA_TREINO_POSICAO, -1);
        if (posicao < 0 || posicao >= AppData.treinos.size()) {
            finish();
            return;
        }
        treino = AppData.treinos.get(posicao);

        tvNomeTreino = findViewById(R.id.tvNomeTreino);
        rvRegistros = findViewById(R.id.rvRegistros);
        btFinalizarTreino = findViewById(R.id.btFinalizarTreino);

        tvNomeTreino.setText(treino.getNome());

        RegistroExecucaoAdapter adapter = new RegistroExecucaoAdapter(treino.getRegistros());
        rvRegistros.setLayoutManager(new LinearLayoutManager(this));
        rvRegistros.setAdapter(adapter);

        btFinalizarTreino.setOnClickListener(v -> {
            boolean todosConcluidos = true;
            for (RegistroExecucao r : treino.getRegistros()) {
                if (!r.isConcluido()) {
                    todosConcluidos = false;
                    break;
                }
            }

            // RN004 - só finaliza se TODOS os exercícios estiverem concluídos
            if (!todosConcluidos) {
                Toast.makeText(this, "Marque todos os exercícios como concluídos antes de finalizar!", Toast.LENGTH_SHORT).show();
                return;
            }

            // RF08 - marcação do treino como concluído
            treino.setConcluido(true);
            treino.setDataExecucao(System.currentTimeMillis());

            // Persiste a atualização no banco Room
            AppRepository.atualizarTreino(this, treino);

            Toast.makeText(this, "Treino concluído!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
