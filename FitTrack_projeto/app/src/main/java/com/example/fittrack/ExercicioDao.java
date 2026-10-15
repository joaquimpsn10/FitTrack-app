package com.example.fittrack;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExercicioDao {

    @Query("SELECT * FROM exercicios")
    List<ExercicioEntity> getAll();

    @Insert
    long insert(ExercicioEntity exercicio);

    @Query("DELETE FROM exercicios WHERE nome = :nome")
    void deleteByNome(String nome);
}
