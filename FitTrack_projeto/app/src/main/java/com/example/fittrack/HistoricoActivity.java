package com.example.fittrack;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoricoActivity extends AppCompatActivity {

    RecyclerView rvHistorico;
    View btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        btVoltar = findViewById(R.id.btVoltar);
        if (btVoltar != null) {
            btVoltar.setOnClickListener(v -> finish());
        }

        rvHistorico = findViewById(R.id.rvHistorico);

        // RF09 / RF11 - lista os treinos já concluídos (histórico de treinamentos)
        List<Treino> concluidos = new ArrayList<>();
        for (Treino t : AppData.treinos) {
            if (t.isConcluido()) {
                concluidos.add(t);
            }
        }

        // RN005 - ordem cronológica, do mais recente para o mais antigo
        Collections.sort(concluidos, (a, b) -> Long.compare(b.getDataExecucao(), a.getDataExecucao()));

        HistoricoAdapter adapter = new HistoricoAdapter(concluidos);
        rvHistorico.setLayoutManager(new LinearLayoutManager(this));
        rvHistorico.setAdapter(adapter);
    }
}
