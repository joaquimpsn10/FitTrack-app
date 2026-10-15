package com.example.fittrack;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "registros_execucao")
public class RegistroExecucaoEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int treinoId;
    public String exercicioNome;
    public String exercicioGrupo;
    public int series;
    public int repeticoes;
    public double carga;
    public boolean concluido;

    public RegistroExecucaoEntity(int treinoId, String exercicioNome, String exercicioGrupo, int series, int repeticoes, double carga, boolean concluido) {
        this.treinoId = treinoId;
        this.exercicioNome = exercicioNome;
        this.exercicioGrupo = exercicioGrupo;
        this.series = series;
        this.repeticoes = repeticoes;
        this.carga = carga;
        this.concluido = concluido;
    }
}
