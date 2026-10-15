package com.example.fittrack;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "treinos")
public class TreinoEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nome;
    public boolean concluido;
    public long dataExecucao;

    public TreinoEntity(String nome, boolean concluido, long dataExecucao) {
        this.nome = nome;
        this.concluido = concluido;
        this.dataExecucao = dataExecucao;
    }
}
