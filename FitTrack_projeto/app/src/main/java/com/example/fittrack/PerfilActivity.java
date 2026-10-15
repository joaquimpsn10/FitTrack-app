package com.example.fittrack;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    TextView tvNomeSaudacao, tvStatTreinos, tvStatExercicios, tvStatMeta;
    EditText etNomeUsuario, etPeso, etAltura, etMetaSemanal;
    Button btSalvarPerfil;
    View btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        btVoltar = findViewById(R.id.btVoltar);
        if (btVoltar != null) {
            btVoltar.setOnClickListener(v -> finish());
        }

        tvNomeSaudacao = findViewById(R.id.tvNomeSaudacao);
        tvStatTreinos = findViewById(R.id.tvStatTreinos);
        tvStatExercicios = findViewById(R.id.tvStatExercicios);
        tvStatMeta = findViewById(R.id.tvStatMeta);

        etNomeUsuario = findViewById(R.id.etNomeUsuario);
        etPeso = findViewById(R.id.etPeso);
        etAltura = findViewById(R.id.etAltura);
        etMetaSemanal = findViewById(R.id.etMetaSemanal);
        btSalvarPerfil = findViewById(R.id.btSalvarPerfil);

        carregarDados();

        // RF12 - gerenciamento das informações do usuário
        btSalvarPerfil.setOnClickListener(v -> {
            String nome = etNomeUsuario.getText().toString().trim();
            String peso = etPeso.getText().toString().trim();
            String altura = etAltura.getText().toString().trim();
            String meta = etMetaSemanal.getText().toString().trim();

            if (nome.isEmpty()) {
                Toast.makeText(this, "Digite seu nome!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Persiste nas SharedPreferences e no AppData
            PerfilPreferences.salvar(this, nome, peso, altura, meta);

            atualizarSaudacaoEStats();
            Toast.makeText(this, "Perfil atualizado com sucesso!", Toast.LENGTH_SHORT).show();
        });
    }

    private void carregarDados() {
        PerfilPreferences.carregar(this);

        if (!AppData.nomeUsuario.isEmpty()) {
            etNomeUsuario.setText(AppData.nomeUsuario);
        }
        if (!AppData.peso.isEmpty()) {
            etPeso.setText(AppData.peso);
        }
        if (!AppData.altura.isEmpty()) {
            etAltura.setText(AppData.altura);
        }
        if (!AppData.metaSemanal.isEmpty()) {
            etMetaSemanal.setText(AppData.metaSemanal);
        }

        atualizarSaudacaoEStats();
    }

    private void atualizarSaudacaoEStats() {
        if (!AppData.nomeUsuario.isEmpty()) {
            tvNomeSaudacao.setText("Olá, " + AppData.nomeUsuario + "! 👋");
        } else {
            tvNomeSaudacao.setText("Olá, Atleta! 👋");
        }

        int concluidos = 0;
        for (Treino t : AppData.treinos) {
            if (t.isConcluido()) {
                concluidos++;
            }
        }

        tvStatTreinos.setText(String.valueOf(concluidos));
        tvStatExercicios.setText(String.valueOf(AppData.exercicios.size()));
        tvStatMeta.setText(AppData.metaSemanal + "x/sem");
    }
}
