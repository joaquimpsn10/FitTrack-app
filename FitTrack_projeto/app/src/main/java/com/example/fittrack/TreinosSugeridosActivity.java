package com.example.fittrack;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreinosSugeridosActivity extends AppCompatActivity {

    RecyclerView rvTreinosSugeridos;
    View btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treinos_sugeridos);

        btVoltar = findViewById(R.id.btVoltar);
        if (btVoltar != null) {
            btVoltar.setOnClickListener(v -> finish());
        }

        rvTreinosSugeridos = findViewById(R.id.rvTreinosSugeridos);

        List<TreinoSugerido> sugeridos = gerarTreinosSugeridos();

        TreinoSugeridoAdapter adapter = new TreinoSugeridoAdapter(sugeridos, treinoSugerido -> {
            // Adiciona exercícios à lista global se não existirem
            for (Exercicio e : treinoSugerido.getExercicios()) {
                boolean jaExiste = false;
                for (Exercicio existente : AppData.exercicios) {
                    if (existente.getNome().equalsIgnoreCase(e.getNome())) {
                        jaExiste = true;
                        break;
                    }
                }
                if (!jaExiste) {
                    AppData.exercicios.add(e);
                    AppRepository.salvarExercicio(this, e);
                }
            }

            // Cria o treino e adiciona aos treinos do usuário
            Treino novoTreino = new Treino(treinoSugerido.getDiaSemana() + " - " + treinoSugerido.getTitulo());
            novoTreino.getExercicios().addAll(treinoSugerido.getExercicios());

            AppData.treinos.add(novoTreino);
            AppRepository.salvarTreino(this, novoTreino);

            Toast.makeText(this, treinoSugerido.getDiaSemana() + " adicionado aos seus treinos!", Toast.LENGTH_SHORT).show();
        });

        rvTreinosSugeridos.setLayoutManager(new LinearLayoutManager(this));
        rvTreinosSugeridos.setAdapter(adapter);
    }

    private List<TreinoSugerido> gerarTreinosSugeridos() {
        List<TreinoSugerido> lista = new ArrayList<>();

        // Segunda
        lista.add(new TreinoSugerido("Segunda-feira", "Treino A - Peito e Tríceps", Arrays.asList(
                new Exercicio("Supino Reto com Barra", "Peito"),
                new Exercicio("Supino Inclinado com Halteres", "Peito"),
                new Exercicio("Tríceps Pulley", "Tríceps"),
                new Exercicio("Tríceps Testa", "Tríceps")
        )));

        // Terça
        lista.add(new TreinoSugerido("Terça-feira", "Treino B - Costas e Bíceps", Arrays.asList(
                new Exercicio("Puxada Frontal", "Costas"),
                new Exercicio("Remada Curvada com Barra", "Costas"),
                new Exercicio("Rosca Direta", "Bíceps"),
                new Exercicio("Rosca Martelo", "Bíceps")
        )));

        // Quarta
        lista.add(new TreinoSugerido("Quarta-feira", "Treino C - Pernas e Ombros", Arrays.asList(
                new Exercicio("Agachamento Livre", "Pernas"),
                new Exercicio("Leg Press 45°", "Pernas"),
                new Exercicio("Desenvolvimento com Halteres", "Ombros"),
                new Exercicio("Elevação Lateral", "Ombros")
        )));

        // Quinta
        lista.add(new TreinoSugerido("Quinta-feira", "Treino D - Abdômen e Cardio", Arrays.asList(
                new Exercicio("Abdominal Infra", "Cór"),
                new Exercicio("Prancha Ventral", "Cór"),
                new Exercicio("Elevação de Pernas", "Cór"),
                new Exercicio("Esteira / Aeróbico", "Cardio")
        )));

        // Sexta
        lista.add(new TreinoSugerido("Sexta-feira", "Treino E - Superior e Full Body", Arrays.asList(
                new Exercicio("Flexão de Braço", "Peito/Tríceps"),
                new Exercicio("Remada Unilateral com Halter", "Costas"),
                new Exercicio("Mergulho no Banco", "Tríceps"),
                new Exercicio("Encolhimento de Ombros", "Trapézio")
        )));

        return lista;
    }
}
