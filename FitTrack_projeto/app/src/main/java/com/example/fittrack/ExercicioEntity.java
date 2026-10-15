package com.example.fittrack;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercicios")
public class ExercicioEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nome;
    public String grupoMuscular;

    public ExercicioEntity(String nome, String grupoMuscular) {
        this.nome = nome;
        this.grupoMuscular = grupoMuscular;
    }
}
