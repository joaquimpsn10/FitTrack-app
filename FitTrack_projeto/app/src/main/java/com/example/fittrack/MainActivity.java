package com.example.fittrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    View btExercicios, btTreinos, btHistorico, btPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Carrega os dados salvos do banco de dados Room e SharedPreferences
        AppRepository.carregarTudo(this, null);

        btExercicios = findViewById(R.id.btExercicios);
        btTreinos = findViewById(R.id.btTreinos);
        btHistorico = findViewById(R.id.btHistorico);
        btPerfil = findViewById(R.id.btPerfil);

        btExercicios.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ExerciciosActivity.class)));

        btTreinos.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, TreinosActivity.class)));

        btHistorico.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, HistoricoActivity.class)));

        btPerfil.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, PerfilActivity.class)));
    }
}
